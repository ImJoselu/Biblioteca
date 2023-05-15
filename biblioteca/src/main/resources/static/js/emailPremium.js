'use strict'

function enviarCorreoPagoPremium() {
	var correo_to = document.getElementById("correoElectronicoUser").innerText;
	var mensaje = document.getElementById("correoMensajeUser").innerText;
	var username = document.getElementById("correoNombreUser").innerText;

	const serviceID = 'service_sfbcp59';
	const templateID = 'template_mcesusi';
	const userID = 'GFWmIzQl29O9z5nWR';

	emailjs.init("GFWmIzQl29O9z5nWR");
	emailjs.send("service_sfbcp59", "template_otlr8qu", {
		to_name: username,
		message: mensaje,
		to_email: correo_to,
	}

	)
		.then((response) => {
			console.log('Correo electrónico enviado exitosamente');
			console.log(response);
		}, (error) => {
			console.log('Error al enviar el correo electrónico', error);
		});
}

function enviarCorreoCancelacionPremium() {
	var correo_to = document.getElementById("correoElectronicoUser").innerText;
	var mensaje = document.getElementById("correoMensajeUser").innerText;
	var username = document.getElementById("correoNombreUser").innerText;

	const serviceID = 'service_sfbcp59';
	const templateID = 'template_mcesusi';
	const userID = 'GFWmIzQl29O9z5nWR';

	emailjs.init("GFWmIzQl29O9z5nWR");
	emailjs.send("service_sfbcp59", "template_otlr8qu", {
		to_name: username,
		message: mensaje,
		to_email: correo_to,
	}

	)
		.then((response) => {
			console.log('Correo electrónico enviado exitosamente');
			console.log(response);
		}, (error) => {
			console.log('Error al enviar el correo electrónico', error);
		});
}