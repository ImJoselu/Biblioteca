/*
async function obtenerDatos() {
  const respuesta = await fetch('https://ejemplo.com/datos');
  const datos = await respuesta.json();
  return datos;
}
*/
const selectAños = document.getElementById("selectAnyo");

llamadaAlquiladosPorMes(selectAños.options[selectAños.selectedIndex].value);
selectAños.addEventListener("change", function () {
  const selectedValue = selectAños.options[selectAños.selectedIndex].value;
  console.log("El valor seleccionado es: " + selectedValue);
  // Aquí podrías agregar cualquier otra acción que quieras que suceda al cambiar el valor del select
  window.lineChart.destroy();
  llamadaAlquiladosPorMes(selectedValue);
});

const integerYAxis = {
  scales: {
    y: {
      ticks: {
        beginAtZero: true,
        stepSize: 1,// establece el intervalo entre cada línea en el eje Y a 1
      }
    }
  }
};

function librosPopulares(datos) {

  new Chart(
    document.getElementById('barChart'),
    {
      type: 'bar',
      data: {
        labels: datos.labels,
        datasets: [
          {
            label: 'Veces Alquilado en el ultimo Año',
            data: datos.data
          }
        ]
      },
      options: {

        scales: {
          y: {
            ticks: {
              beginAtZero: true,
              stepSize: 1,// establece el intervalo entre cada línea en el eje Y a 1
            }
          }
        }
      }
    }

  )
}

fetch("http://localhost:8888/ws/estadisticas/librosPopulares")
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al obtener los datos');
    }

    console.log("Estado: " + response.status);
    return response.json();
  })
  .then(data => {
    librosPopulares(data);
    console.log(data);
    // hacer algo con los datos en formato JSON
  })
  .catch(error => {
    // manejar el error
    console.log("Error");
  });


fetch("http://localhost:8888/ws/estadisticas/generosPopulares")
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al obtener los datos');
    }

    console.log("Estado: " + response.status);
    return response.json();
  })
  .then(data => {
    generosPopulares(data);
    console.log(data);
    // hacer algo con los datos en formato JSON
  })
  .catch(error => {
    // manejar el error
    console.log("Error");
  });


function llamadaAlquiladosPorMes(valorAño) {
  const url = new URL("http://localhost:8888/ws/estadisticas/alquiladosPorMes");
  url.searchParams.set('anyo', parseInt(valorAño));

console.log(url);

  fetch(url, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }

  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al obtener los datos');
      }

      console.log("Estado: " + response.status);
      return response.json();
    })
    .then(data => {
      alquileresPorMes(data);
      console.log(data);
      // hacer algo con los datos en formato JSON
    })
    .catch(error => {
      // manejar el error
      console.log("Error");
      console.log(error);
    });
}



function generosPopulares(datos) {
  const data = {
    labels: datos.labels,
    datasets: [{
      label: 'Generos Populares',
      data: datos.data,
      backgroundColor: getDataColors(),
      borderColor: getDataColors(50),
      hoverOffset: 10
    }]
  };

  const options = {
    plugins: {
      legend: { position: 'left' }
    }
  }

  const config = {
    type: 'doughnut',
    data: data,
    options: options
  };


  new Chart(
    document.getElementById('pieChart'),
    config

  );

}

function alquileresPorMes(datos) {

  const labels = datos.labels;
  const data = {
    labels: labels,
    datasets: [{
      label: 'Alquileres',
      data: datos.data,
      fill: false,
      borderColor: 'rgb(75, 192, 192)',
      tension: 0.1
    }]
  };

  const config = {
    type: 'line',
    data: data,
    options: integerYAxis
  };

  
  window.lineChart = new Chart(
    document.getElementById('lineChart'),
    config

  );
}

