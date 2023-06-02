
document.addEventListener("DOMContentLoaded", () => {
	const $resultados = document.querySelector("#contenedorIsbn");
	Quagga.init({
		inputStream: {
			constraints: {
				width: 1920,
				height: 1080,
			},
			name: "Live",
			type: "LiveStream",
			target: document.querySelector('#contenedorIsbn'), // Pasar el elemento del DOM
		},
		decoder: {
			readers: ["ean_reader"]
		}
	}, function (err) {
		if (err) {
			console.log(err);
			return
		}
		console.log("Iniciado correctamente");
		Quagga.start();
	});

	Quagga.onDetected((data) => {
		$resultados.value = data.codeResult.code;
		// Imprimimos todo el data para que puedas depurar
		console.log(data);
	});
});