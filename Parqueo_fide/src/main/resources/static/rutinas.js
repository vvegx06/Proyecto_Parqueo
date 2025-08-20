/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function readURL(input) {
    // Verifica que se haya seleccionado un archivo
    if (input.files && input.files[0]) {
        var reader = new FileReader(); // Crea un objeto FileReader para leer archivos
        
        // Evento que se dispara cuando se termina de leer el archivo
        reader.onload = function (e) {
            $('#blah') // Selecciona el elemento con id="blah"
                .attr('src', e.target.result) // Cambia la fuente de la imagen al contenido leído
                .height(200); // Ajusta la altura de la imagen a 200px
        };
        
        // Lee el archivo seleccionado como una URL de datos (base64)
        reader.readAsDataURL(input.files[0]);
    }
}
