/*
const selectAños = document.getElementById("selectAños");

llamadaAlquiladosPorMes(selectAños.options[selectAños.selectedIndex].value);

selectAños.addEventListener("change", function () {
  const selectedValue = selectAños.options[selectAños.selectedIndex].value;
  console.log("El valor seleccionado es: " + selectedValue);
  // Aquí podrías agregar cualquier otra acción que quieras que suceda al cambiar el valor del select
  window.lineChart.destroy();
  llamadaAlquiladosPorMes(selectedValue);
});
*/
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


  try{
    librosPopulares(libPopulares);
    console.log(libPopulares);
    // hacer algo con los datos en formato JSON
  }
  catch(error){
    // manejar el error
    console.log("Error");
  };
  
  try{
    generosPopulares(genPopulares);
    console.log(genPopulares);
    // hacer algo con los datos en formato JSON
  }
  catch(error){
    // manejar el error
    console.log("Error");
  };
  
    try{
		alquileresPorMes(alqMes);
      	console.log(alqMes);
    // hacer algo con los datos en formato JSON
  }
  catch(error){
    // manejar el error
    console.log("Error");
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

