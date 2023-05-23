package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xbib.marc.Marc;
import org.xbib.marc.MarcField;
import org.xbib.marc.MarcFieldAdapter;
import org.xbib.marc.MarcListener;
import org.yaz4j.Connection;
import org.yaz4j.PrefixQuery;
import org.yaz4j.Query;
import org.yaz4j.Record;
import org.yaz4j.ResultSet;
import org.yaz4j.exception.ZoomException;

import com.example.demo.repository.dao.EditorialRepository;
import com.example.demo.repository.dao.LibroRepository;
import com.example.demo.repository.entity.Editorial;
import com.example.demo.repository.entity.Libro;
import com.example.demo.repository.entity.LibroRebeca;

@Service
public class RebecaServiceImpl implements RebecaService{
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private EditorialRepository editorialRepository;
	
	@Override
	public boolean save(LibroRebeca libroRebeca) {
		
		try {
			Libro libro = LibroRebeca.convertToLibro(libroRebeca);
			
			Libro libroBuscado = libroRepository.findByISBN(libroRebeca.getIsbn13());
			Editorial edBuscada = editorialRepository.findByNombre(libroRebeca.getPublicacion());
			if(libroBuscado == null) {
				if(edBuscada == null) {
					editorialRepository.save(libro.getEditorial());
				}
				libroRepository.save(libro);
			}else {
				return false;
			}
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		
	}
	

	@Override
public LibroRebeca performSearch(String isbn13) {
		// TODO Auto-generated method stub
		try {
			byte[] bites = consultaRebeca(isbn13);
			
			
			if(bites == null) {
				return null;
			}
			
			//En la consulta mejor coger el ultimo elemento de la lista, tal vez se mas reciente.

			LibroRebeca libroRebeca = new LibroRebeca();
			
			String copyRight = findCopy(bites);
			String isbn = findISBN(bites);
			String catSource = findCatSource(bites);
			String UDSN = findUDSN(bites);
			String author = findAuthor(bites);
			String title = findTitle(bites);
			String edicion = findEdicion(bites);
			String publicacion = findPublisher(bites);
			String fisico = findFisico(bites);
			String notas = findNotas(bites);
			String genero = findGenero(bites);
			
			libroRebeca.setCopyright(copyRight);
			libroRebeca.setIsbn13(isbn);
			libroRebeca.setCatSource(catSource);
			libroRebeca.setUdsn(UDSN);
			libroRebeca.setAutor(author);
			libroRebeca.setTitulo(title);
			libroRebeca.setEdicion(edicion);
			libroRebeca.setPublicacion(publicacion);
			libroRebeca.setDatosFisicos(fisico);
			libroRebeca.setNotas(notas);
			libroRebeca.setGenero(genero);
			
			return libroRebeca;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
@SuppressWarnings("finally")
public byte[] consultaRebeca(String isbn13) throws IOException {

	
		Connection conn = new Connection("catalogos.mecd.es", 220);
		ResultSet rs = null;
		byte[] bites = null;
		try {
			conn.setDatabaseName("ABNET_REBECA");
			conn.setSyntax("USmarc");
			System.out.println("Funciona 1");
			conn.connect();
			System.out.println("Funciona 2");
			
			
			//1=5 TITULO
			//1=7 ISBN13 \"978-84-2720-213-9\", \"978-84-7888-445-2\" /Solo 84 (España)
			//979-84-2642-080-9
			Query query = new PrefixQuery("@attr 1=7 \"" + isbn13 + "\"");
			//Query query = new PrefixQuery(" 'title' contains 'galaxy' ");
			
			System.out.println("Funciona 3");
			rs = conn.search(query);
			
	        System.out.println("Funciona 4");
	        
	        Iterator<Record> it = rs.iterator();
	        //Cogemos el primer record
	        if (it.hasNext()) {
	        	//System.out.println("Existen Records");
	        	Record record = it.next(); 
	        	bites = record.getContent();
	        	
	        	String content = new String(bites, StandardCharsets.ISO_8859_1);
	        	bites = content.getBytes();
	        
	        }
		}catch(ZoomException  e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.toString());
		}
		finally {
			
			rs.close();
			conn.close();
		}
		
		return bites;
		
		
		
	}


//--------------------------------- COPYRIGHT 017 a --------------------------------------------


public String findCopy(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
    Map<String, List<Map<String, String>>> result = new TreeMap<>();
    // set up MARC listener
    MarcListener marcListener = new MarcFieldAdapter() {
        @Override
        public void field(MarcField field) {
        	System.out.println(field);
            Collection<Map<String, String>> values = field.getSubfields().stream()
                    .filter(f -> matchCopyField(field, f))
                    .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
                    .collect(Collectors.toList());
            if (!values.isEmpty()) {
                result.putIfAbsent(field.getTag(), new ArrayList<>());
                List<Map<String, String>> list = result.get(field.getTag());
                list.addAll(values);
                result.put(field.getTag(), list);
            }
        }
    };
    // read MARC file
    Marc.builder()
            .setInputStream(in)
            .setMarcListener(marcListener)
            .build()
            .writeCollection();
    // collect ISSNs
    List<String> issns = result.values().stream()
            .map(l -> l.stream()
                    .map(m -> m.values().iterator().next())
                    .collect(Collectors.toList()))
            .flatMap(List::stream)
            .distinct()
            .collect(Collectors.toList());
    
    for (String string : issns) {
		System.out.println(string);
	}
    
    in.close();
    
    if(issns.isEmpty()) {
    	return null;
    }else {
    	return issns.get(0);
    }
    

}

private static boolean matchCopyField(MarcField field, MarcField.Subfield subfield) {
    switch (field.getTag()) {
    //011-> ISSN
    //020-> ISBN
    //245-> Title Statement
    //017 - Copyright or Legal Deposit Number (R)
    //100 - Main Entry - Personal Name (Autor)
    //260 - Publication, Distribution, etc. (Imprint)
    //490 - coleccion + version (v2)
    //700 - Traduccion
        case "017": {
            return "a".equals(subfield.getId());
        }
        case "421":
        case "451":
        case "452":
        case "488":
            return "x".equals(subfield.getId());
    }
    return false;
}



//--------------------------------- ISBN 020 a/z -------------------------------------------



public String findISBN(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
    Map<String, List<Map<String, String>>> result = new TreeMap<>();
    // set up MARC listener
    MarcListener marcListener = new MarcFieldAdapter() {
        @Override
        public void field(MarcField field) {
        	System.out.println(field);
            Collection<Map<String, String>> values = field.getSubfields().stream()
                    .filter(f -> matchISBNField(field, f))
                    .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
                    .collect(Collectors.toList());
            if (!values.isEmpty()) {
                result.putIfAbsent(field.getTag(), new ArrayList<>());
                List<Map<String, String>> list = result.get(field.getTag());
                list.addAll(values);
                result.put(field.getTag(), list);
            }
        }
    };
    // read MARC file
    Marc.builder()
            .setInputStream(in)
            .setMarcListener(marcListener)
            .build()
            .writeCollection();
    // collect ISSNs
    List<String> issns = result.values().stream()
            .map(l -> l.stream()
                    .map(m -> m.values().iterator().next())
                    .collect(Collectors.toList()))
            .flatMap(List::stream)
            .distinct()
            .collect(Collectors.toList());
    
    for (String string : issns) {
		System.out.println(string);
	}
    
    in.close();
    
    if(issns.isEmpty()) {
    	return null;
    }else {
    	return issns.get(0);
    }

}

private static boolean matchISBNField(MarcField field, MarcField.Subfield subfield) {
    switch (field.getTag()) {
    //011-> ISSN
    //020-> ISBN
    //245-> Title Statement
    //017 - Copyright or Legal Deposit Number (R)
    //100 - Main Entry - Personal Name (Autor)
    //260 - Publication, Distribution, etc. (Imprint)
    //490 - coleccion + version (v2)
    //700 - Traduccion
        case "020": {
            return "a".equals(subfield.getId()) || "z".equals(subfield.getId());
        }
        case "421":
        case "451":
        case "452":
        case "488":
            return "x".equals(subfield.getId());
    }
    return false;
}


//-------------------------------- CAT SOURCE 040 a --------------------------------------------



public String findCatSource(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
  Map<String, List<Map<String, String>>> result = new TreeMap<>();
  // set up MARC listener
  MarcListener marcListener = new MarcFieldAdapter() {
      @Override
      public void field(MarcField field) {
      	System.out.println(field);
          Collection<Map<String, String>> values = field.getSubfields().stream()
                  .filter(f -> matchCatSourceField(field, f))
                  .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
                  .collect(Collectors.toList());
          if (!values.isEmpty()) {
              result.putIfAbsent(field.getTag(), new ArrayList<>());
              List<Map<String, String>> list = result.get(field.getTag());
              list.addAll(values);
              result.put(field.getTag(), list);
          }
      }
  };
  // read MARC file
  Marc.builder()
          .setInputStream(in)
          .setMarcListener(marcListener)
          .build()
          .writeCollection();
  // collect ISSNs
  List<String> issns = result.values().stream()
          .map(l -> l.stream()
                  .map(m -> m.values().iterator().next())
                  .collect(Collectors.toList()))
          .flatMap(List::stream)
          .distinct()
          .collect(Collectors.toList());
  
  for (String string : issns) {
		System.out.println(string);
	}
  
  in.close();
  
  if(issns.isEmpty()) {
  	return null;
  }else {
  	return issns.get(0);
  }

}

private static boolean matchCatSourceField(MarcField field, MarcField.Subfield subfield) {
  switch (field.getTag()) {
  //011-> ISSN
  //020-> ISBN
  //245-> Title Statement
  //017 - Copyright or Legal Deposit Number (R)
  //100 - Main Entry - Personal Name (Autor)
  //260 - Publication, Distribution, etc. (Imprint)
  //490 - coleccion + version (v2)
  //700 - Traduccion
      case "040": {
          return "a".equals(subfield.getId());
      }
      case "421":
      case "451":
      case "452":
      case "488":
          return "x".equals(subfield.getId());
  }
  return false;
}


//------------------------------ UDSN 80 a ----------------------------------------------


public String findUDSN(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
  Map<String, List<Map<String, String>>> result = new TreeMap<>();
  // set up MARC listener
  MarcListener marcListener = new MarcFieldAdapter() {
      @Override
      public void field(MarcField field) {
      	System.out.println(field);
          Collection<Map<String, String>> values = field.getSubfields().stream()
                  .filter(f -> matchUDSNField(field, f))
                  .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
                  .collect(Collectors.toList());
          if (!values.isEmpty()) {
              result.putIfAbsent(field.getTag(), new ArrayList<>());
              List<Map<String, String>> list = result.get(field.getTag());
              list.addAll(values);
              result.put(field.getTag(), list);
          }
      }
  };
  // read MARC file
  Marc.builder()
          .setInputStream(in)
          .setMarcListener(marcListener)
          .build()
          .writeCollection();
  // collect ISSNs
  List<String> issns = result.values().stream()
          .map(l -> l.stream()
                  .map(m -> m.values().iterator().next())
                  .collect(Collectors.toList()))
          .flatMap(List::stream)
          .distinct()
          .collect(Collectors.toList());
  
  for (String string : issns) {
		System.out.println(string);
	}
  
  in.close();
  
  if(issns.isEmpty()) {
  	return null;
  }else {
  	return issns.get(0);
  }

}

private static boolean matchUDSNField(MarcField field, MarcField.Subfield subfield) {
  switch (field.getTag()) {
  //011-> ISSN
  //020-> ISBN
  //245-> Title Statement
  //017 - Copyright or Legal Deposit Number (R)
  //100 - Main Entry - Personal Name (Autor)
  //260 - Publication, Distribution, etc. (Imprint)
  //490 - coleccion + version (v2)
  //700 - Traduccion
      case "080": {
          return "a".equals(subfield.getId());
      }
      case "421":
      case "451":
      case "452":
      case "488":
          return "x".equals(subfield.getId());
  }
  return false;
}



//------------------------------ AUTHOR 100 ad ----------------------------------------------



public String findAuthor(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
Map<String, List<Map<String, String>>> result = new TreeMap<>();
// set up MARC listener
MarcListener marcListener = new MarcFieldAdapter() {
    @Override
    public void field(MarcField field) {
    	System.out.println(field);
        Collection<Map<String, String>> values = field.getSubfields().stream()
                .filter(f -> matchAuthorField(field, f))
                .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
                .collect(Collectors.toList());
        if (!values.isEmpty()) {
            result.putIfAbsent(field.getTag(), new ArrayList<>());
            List<Map<String, String>> list = result.get(field.getTag());
            list.addAll(values);
            result.put(field.getTag(), list);
        }
    }
};
// read MARC file
Marc.builder()
        .setInputStream(in)
        .setMarcListener(marcListener)
        .build()
        .writeCollection();
// collect ISSNs
List<String> issns = result.values().stream()
        .map(l -> l.stream()
                .map(m -> m.values().iterator().next())
                .collect(Collectors.toList()))
        .flatMap(List::stream)
        .distinct()
        .collect(Collectors.toList());

for (String string : issns) {
		System.out.println(string);
	}

in.close();

if(issns.isEmpty()) {
	return null;
}else {
	StringBuilder stringBuilder = new StringBuilder();
	for (String string : issns) {
		stringBuilder.append(string);
	}
	return stringBuilder.toString();
}

}

private static boolean matchAuthorField(MarcField field, MarcField.Subfield subfield) {
switch (field.getTag()) {
//011-> ISSN
//020-> ISBN
//245-> Title Statement
//017 - Copyright or Legal Deposit Number (R)
//100 - Main Entry - Personal Name (Autor)
//260 - Publication, Distribution, etc. (Imprint)
//490 - coleccion + version (v2)
//700 - Traduccion
    case "100": {
        return "a".equals(subfield.getId()) || "d".equals(subfield.getId());
    }
    case "421":
    case "451":
    case "452":
    case "488":
        return "x".equals(subfield.getId());
}
return false;
}


//------------------------------ TITLE 245 ac ----------------------------------------------



public String findTitle(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
Map<String, List<Map<String, String>>> result = new TreeMap<>();
//set up MARC listener
MarcListener marcListener = new MarcFieldAdapter() {
  @Override
  public void field(MarcField field) {
  	System.out.println(field);
      Collection<Map<String, String>> values = field.getSubfields().stream()
              .filter(f -> matchTitleField(field, f))
              .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
              .collect(Collectors.toList());
      if (!values.isEmpty()) {
          result.putIfAbsent(field.getTag(), new ArrayList<>());
          List<Map<String, String>> list = result.get(field.getTag());
          list.addAll(values);
          result.put(field.getTag(), list);
      }
  }
};
//read MARC file
Marc.builder()
      .setInputStream(in)
      .setMarcListener(marcListener)
      .build()
      .writeCollection();
//collect ISSNs
List<String> issns = result.values().stream()
      .map(l -> l.stream()
              .map(m -> m.values().iterator().next())
              .collect(Collectors.toList()))
      .flatMap(List::stream)
      .distinct()
      .collect(Collectors.toList());

for (String string : issns) {
		System.out.println(string);
	}

in.close();

if(issns.isEmpty()) {
	return null;
}else {
	StringBuilder stringBuilder = new StringBuilder();
	for (String string : issns) {
		stringBuilder.append(string);
	}
	return stringBuilder.toString();
}

}

private static boolean matchTitleField(MarcField field, MarcField.Subfield subfield) {
switch (field.getTag()) {
//011-> ISSN
//020-> ISBN
//245-> Title Statement
//017 - Copyright or Legal Deposit Number (R)
//100 - Main Entry - Personal Name (Autor)
//260 - Publication, Distribution, etc. (Imprint)
//490 - coleccion + version (v2)
//700 - Traduccion
  case "245": {
      return "a".equals(subfield.getId()) || "c".equals(subfield.getId());
  }
  case "421":
  case "451":
  case "452":
  case "488":
      return "x".equals(subfield.getId());
}
return false;
}


//--------------------------------- EDICION 250 a --------------------------------------------


public String findEdicion(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
  Map<String, List<Map<String, String>>> result = new TreeMap<>();
  // set up MARC listener
  MarcListener marcListener = new MarcFieldAdapter() {
      @Override
      public void field(MarcField field) {
      	System.out.println(field);
          Collection<Map<String, String>> values = field.getSubfields().stream()
                  .filter(f -> matchEdicionField(field, f))
                  .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
                  .collect(Collectors.toList());
          if (!values.isEmpty()) {
              result.putIfAbsent(field.getTag(), new ArrayList<>());
              List<Map<String, String>> list = result.get(field.getTag());
              list.addAll(values);
              result.put(field.getTag(), list);
          }
      }
  };
  // read MARC file
  Marc.builder()
          .setInputStream(in)
          .setMarcListener(marcListener)
          .build()
          .writeCollection();
  // collect ISSNs
  List<String> issns = result.values().stream()
          .map(l -> l.stream()
                  .map(m -> m.values().iterator().next())
                  .collect(Collectors.toList()))
          .flatMap(List::stream)
          .distinct()
          .collect(Collectors.toList());
  
  for (String string : issns) {
		System.out.println(string);
	}
  
  in.close();
  
  if(issns.isEmpty()) {
  	return null;
  }else {
  	return issns.get(0);
  }
  

}

private static boolean matchEdicionField(MarcField field, MarcField.Subfield subfield) {
  switch (field.getTag()) {
  //011-> ISSN
  //020-> ISBN
  //245-> Title Statement
  //017 - Copyright or Legal Deposit Number (R)
  //100 - Main Entry - Personal Name (Autor)
  //260 - Publication, Distribution, etc. (Imprint)
  //490 - coleccion + version (v2)
  //700 - Traduccion
      case "250": {
          return "a".equals(subfield.getId());
      }
      case "421":
      case "451":
      case "452":
      case "488":
          return "x".equals(subfield.getId());
  }
  return false;
}



//------------------------------ PUBLISHER 260 abc ----------------------------------------------



public String findPublisher(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
Map<String, List<Map<String, String>>> result = new TreeMap<>();
//set up MARC listener
MarcListener marcListener = new MarcFieldAdapter() {
  @Override
  public void field(MarcField field) {
  	System.out.println(field);
      Collection<Map<String, String>> values = field.getSubfields().stream()
              .filter(f -> matchPubField(field, f))
              .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
              .collect(Collectors.toList());
      if (!values.isEmpty()) {
          result.putIfAbsent(field.getTag(), new ArrayList<>());
          List<Map<String, String>> list = result.get(field.getTag());
          list.addAll(values);
          result.put(field.getTag(), list);
      }
  }
};
//read MARC file
Marc.builder()
      .setInputStream(in)
      .setMarcListener(marcListener)
      .build()
      .writeCollection();
//collect ISSNs
List<String> issns = result.values().stream()
      .map(l -> l.stream()
              .map(m -> m.values().iterator().next())
              .collect(Collectors.toList()))
      .flatMap(List::stream)
      .distinct()
      .collect(Collectors.toList());

for (String string : issns) {
		System.out.println(string);
	}

in.close();

if(issns.isEmpty()) {
	return null;
}else {
	StringBuilder stringBuilder = new StringBuilder();
	for (String string : issns) {
		stringBuilder.append(string);
	}
	return stringBuilder.toString();
}

}

private static boolean matchPubField(MarcField field, MarcField.Subfield subfield) {
switch (field.getTag()) {
//011-> ISSN
//020-> ISBN
//245-> Title Statement
//017 - Copyright or Legal Deposit Number (R)
//100 - Main Entry - Personal Name (Autor)
//260 - Publication, Distribution, etc. (Imprint)
//490 - coleccion + version (v2)
//700 - Traduccion
  case "260": {
      return "a".equals(subfield.getId()) || "b".equals(subfield.getId()) || "c".equals(subfield.getId());
  }
  case "421":
  case "451":
  case "452":
  case "488":
      return "x".equals(subfield.getId());
}
return false;
}


//------------------------------ PHYSICAL 300 ac ----------------------------------------------



public String findFisico(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
Map<String, List<Map<String, String>>> result = new TreeMap<>();
//set up MARC listener
MarcListener marcListener = new MarcFieldAdapter() {
  @Override
  public void field(MarcField field) {
  	System.out.println(field);
      Collection<Map<String, String>> values = field.getSubfields().stream()
              .filter(f -> matchFisicoField(field, f))
              .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
              .collect(Collectors.toList());
      if (!values.isEmpty()) {
          result.putIfAbsent(field.getTag(), new ArrayList<>());
          List<Map<String, String>> list = result.get(field.getTag());
          list.addAll(values);
          result.put(field.getTag(), list);
      }
  }
};
//read MARC file
Marc.builder()
      .setInputStream(in)
      .setMarcListener(marcListener)
      .build()
      .writeCollection();
//collect ISSNs
List<String> issns = result.values().stream()
      .map(l -> l.stream()
              .map(m -> m.values().iterator().next())
              .collect(Collectors.toList()))
      .flatMap(List::stream)
      .distinct()
      .collect(Collectors.toList());

for (String string : issns) {
		System.out.println(string);
	}

in.close();

if(issns.isEmpty()) {
	return null;
}else {
	StringBuilder stringBuilder = new StringBuilder();
	for (String string : issns) {
		stringBuilder.append(string);
	}
	return stringBuilder.toString();
}

}

private static boolean matchFisicoField(MarcField field, MarcField.Subfield subfield) {
switch (field.getTag()) {
//011-> ISSN
//020-> ISBN
//245-> Title Statement
//017 - Copyright or Legal Deposit Number (R)
//100 - Main Entry - Personal Name (Autor)
//260 - Publication, Distribution, etc. (Imprint)
//490 - coleccion + version (v2)
//700 - Traduccion
  case "300": {
      return "a".equals(subfield.getId()) || "c".equals(subfield.getId());
  }
  case "421":
  case "451":
  case "452":
  case "488":
      return "x".equals(subfield.getId());
}
return false;
}


//------------------------------ NOTES 500 a ----------------------------------------------



public String findNotas(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
Map<String, List<Map<String, String>>> result = new TreeMap<>();
//set up MARC listener
MarcListener marcListener = new MarcFieldAdapter() {
@Override
public void field(MarcField field) {
	System.out.println(field);
    Collection<Map<String, String>> values = field.getSubfields().stream()
            .filter(f -> matchNotesField(field, f))
            .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
            .collect(Collectors.toList());
    if (!values.isEmpty()) {
        result.putIfAbsent(field.getTag(), new ArrayList<>());
        List<Map<String, String>> list = result.get(field.getTag());
        list.addAll(values);
        result.put(field.getTag(), list);
    }
}
};
//read MARC file
Marc.builder()
    .setInputStream(in)
    .setMarcListener(marcListener)
    .build()
    .writeCollection();
//collect ISSNs
List<String> issns = result.values().stream()
    .map(l -> l.stream()
            .map(m -> m.values().iterator().next())
            .collect(Collectors.toList()))
    .flatMap(List::stream)
    .distinct()
    .collect(Collectors.toList());

for (String string : issns) {
		System.out.println(string);
	}

in.close();

if(issns.isEmpty()) {
	return null;
}else {
	StringBuilder stringBuilder = new StringBuilder();
	for (String string : issns) {
		stringBuilder.append(string);
	}
	return stringBuilder.toString();
}

}

private static boolean matchNotesField(MarcField field, MarcField.Subfield subfield) {
switch (field.getTag()) {
//011-> ISSN
//020-> ISBN
//245-> Title Statement
//017 - Copyright or Legal Deposit Number (R)
//100 - Main Entry - Personal Name (Autor)
//260 - Publication, Distribution, etc. (Imprint)
//490 - coleccion + version (v2)
//700 - Traduccion
case "500": {
    return "a".equals(subfield.getId());
}
case "421":
case "451":
case "452":
case "488":
    return "x".equals(subfield.getId());
}
return false;
}


//------------------------------ GENRE 655 a ----------------------------------------------



public String findGenero(byte[] bites) throws IOException {
	InputStream in = new ByteArrayInputStream(bites);
Map<String, List<Map<String, String>>> result = new TreeMap<>();
//set up MARC listener
MarcListener marcListener = new MarcFieldAdapter() {
@Override
public void field(MarcField field) {
	System.out.println(field);
  Collection<Map<String, String>> values = field.getSubfields().stream()
          .filter(f -> matchGenreField(field, f))
          .map(f -> Collections.singletonMap(f.getId(), f.getValue()))
          .collect(Collectors.toList());
  if (!values.isEmpty()) {
      result.putIfAbsent(field.getTag(), new ArrayList<>());
      List<Map<String, String>> list = result.get(field.getTag());
      list.addAll(values);
      result.put(field.getTag(), list);
  }
}
};
//read MARC file
Marc.builder()
  .setInputStream(in)
  .setMarcListener(marcListener)
  .build()
  .writeCollection();
//collect ISSNs
List<String> issns = result.values().stream()
  .map(l -> l.stream()
          .map(m -> m.values().iterator().next())
          .collect(Collectors.toList()))
  .flatMap(List::stream)
  .distinct()
  .collect(Collectors.toList());

for (String string : issns) {
		System.out.println(string);
	}

in.close();

if(issns.isEmpty()) {
	return null;
}else {
	StringBuilder stringBuilder = new StringBuilder();
	for (String string : issns) {
		stringBuilder.append(string);
	}
	return stringBuilder.toString();
}

}

private static boolean matchGenreField(MarcField field, MarcField.Subfield subfield) {
switch (field.getTag()) {
//011-> ISSN
//020-> ISBN
//245-> Title Statement
//017 - Copyright or Legal Deposit Number (R)
//100 - Main Entry - Personal Name (Autor)
//260 - Publication, Distribution, etc. (Imprint)
//490 - coleccion + version (v2)
//700 - Traduccion
case "655": {
  return "a".equals(subfield.getId());
}
case "421":
case "451":
case "452":
case "488":
  return "x".equals(subfield.getId());
}
return false;
}








}
