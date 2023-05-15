'use strict'
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

function enviarCorreoAlquiler() {

	var correo_to = document.getElementById("correoElectronico2").innerText;

	var username = document.getElementById("correoUsername2").innerText;
	var fechaLimite = document.getElementById("correoFecha").innerText;
	var correoFechaIn = document.getElementById("correoFechaIn").innerText;
	var tituloLibro = document.getElementById("correoTitulo").innerText;
	var boton2 = document.getElementById("miBoton2");

boton2.disabled = true;
	const serviceID = 'service_sfbcp59';
	const templateID = 'template_mcesusi';
	const userID = 'GFWmIzQl29O9z5nWR';


	emailjs.init("GFWmIzQl29O9z5nWR");
	emailjs.send("service_sfbcp59", "template_mcesusi", {
		to_name: username,
		message: "Título del libro: " + tituloLibro + "\n" +
			"Fecha de inicio del alquiler: " + correoFechaIn + "\n" +
			"Fecha límite de devolución:  " + fechaLimite + "\n" +
			"Período de alquiler:  14 Dias | 2 Semanas \n",
		to_email: correo_to,
		intro: "¡Gracias por su interés en nuestro servicio de alquiler de libros de la biblioteca! Nos complace confirmar que su solicitud de alquiler del libro " + '"' + tituloLibro + '"' + " ha sido procesada exitosamente.\n A continuación, encontrará los detalles de su alquiler:",
		outro: "Le recordamos que es su responsabilidad devolver el libro en la fecha acordada. Si necesita extender el período de alquiler, si se entrega el libro en mal estado o en una fecha no correspondiente, se procederá con la Normativa de Penalizaciones.",
		titulo: "Confirmación de Alquiler de Libro: " + tituloLibro,
	}

	)
		.then((response) => {
			console.log('Correo electrónico enviado exitosamente');
			console.log(response);
			// Desactivar el botón
			

			// Agregar la clase al botón
			boton2.classList.add("mensaje-despues");
		}, (error) => {
			console.log('Error al enviar el correo electrónico', error);
			boton2.disabled = true;
		});


}



function enviarCorreo() {

	var correo_to = document.getElementById("correoElectronico").innerText;
	var mensaje = document.getElementById("correoMensaje").innerText;
	var username = document.getElementById("correoUsername").innerText;
	var boton = document.getElementById("miBoton");

	const serviceID = 'service_sfbcp59';
	const templateID = 'template_mcesusi';
	const userID = 'GFWmIzQl29O9z5nWR';
	boton.disabled = true;


	emailjs.init("GFWmIzQl29O9z5nWR");
	emailjs.send("service_sfbcp59", "template_mcesusi", {
		to_name: username,
		message: '" ' + mensaje + ' "',
		to_email: correo_to,
		intro: "¡Es un gusto recibir tu solicitud de contacto! Queremos agradecerte por tomarte el tiempo de escribirnos y hacernos saber acerca de:",
		outro: "Nos alegra saber que estás interesado/a en nuestra biblioteca y queremos asegurarte que trabajaremos diligentemente para atender tus necesidades. Nos encargaremos de procesar tu solicitud lo más pronto posible y te informaremos tan pronto como tengamos una respuesta para ti.",
		titulo: "¡Hemos Recibido Tu Solicitud!",
	}

	)
		.then((response) => {
			console.log('Correo electrónico enviado exitosamente');
			console.log(response);
			// Desactivar el botón
		
			// Agregar la clase al botón
			boton.classList.add("mensaje-despues");


		}, (error) => {
			console.log('Error al enviar el correo electrónico', error);
			boton.disabled = true;
		});


}