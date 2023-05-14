'use strict'

function enviarCorreo() {
	/*	var correo_to = document.getElementById("correoElectronico").innerText;
		var mensaje = document.getElementById("correoMensaje").innerText;
		var username = document.getElementById("correoUsername").innerText;
	
	
	
	
	
		const serviceID = 'service_sfbcp59';
		const templateID = 'template_mcesusi';
		const userID = 'GFWmIzQl29O9z5nWR';
	
		const data = {
			to_name: username,
			to_email: correo_to, // dirección de correo electrónico del destinatario
			from_name: 'La Biblioquería',
			message: mensaje
		};
	
		const formData = new FormData();
		formData.append('service_id', serviceID);
		formData.append('template_id', templateID);
		formData.append('user_id', userID);
		formData.append('template_params', JSON.stringify(data));
	
		fetch('https://api.emailjs.com/api/v1.0/email/send-form', {
			method: 'POST',
			body: formData
		})
			.then(response => {
				console.log('Correo electrónico enviado exitosamente');
				console.log(formData);
				console.log(data);
			})
			.catch(error => {
				console.log('Error al enviar el correo electrónico', error);
			});
		
		setTimeout(function(){
			window.location.href = "/";
		}, 100); // La función se llamará después de 1000 milisegundos (1 segundo)
	    
		*/



	var correo_to = document.getElementById("correoElectronico").innerText;
	var mensaje = document.getElementById("correoMensaje").innerText;
	var username = document.getElementById("correoUsername").innerText;

	const serviceID = 'service_sfbcp59';
	const templateID = 'template_mcesusi';
	const userID = 'GFWmIzQl29O9z5nWR';


	emailjs.init("GFWmIzQl29O9z5nWR");
	emailjs.send("service_sfbcp59", "template_mcesusi", {
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
/*
function enviarCorreoAlquiler() {
	
	var correo_to = document.getElementById("correoElectronico").innerText;
	var mensaje = document.getElementById("correoMensaje").innerText;
	var username = document.getElementById("correoUsername").innerText;

	const serviceID = 'service_sfbcp59';
	const templateID = 'template_mcesusi';
	const userID = 'GFWmIzQl29O9z5nWR';


	emailjs.init("GFWmIzQl29O9z5nWR");
	emailjs.send("service_sfbcp59", "template_mcesusi", {
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


}*/