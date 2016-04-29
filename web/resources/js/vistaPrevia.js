////OTRA FORMA DE HACERLO
////window.imagenVacia = 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==';
//window.mostrarVistaPrevia = function mostrarVistaPrevia() {
//
//    var Archivos, Lector;
//    //Para navegadores antiguos
//    if (typeof FileReader !== "function") {
//        jQuery('#infoNombre').text('[Vista previa no disponible]');
//        jQuery('#infoTamaño').text('(su navegador no soporta vista previa!)');
//        return;
//    }
//
//    Archivos = jQuery("#form\\:foto")[0].files;
//    if (Archivos.length > 0) {
//
//        Lector = new FileReader();
//        Lector.onloadend = function (e) {
//            var origen, tipo;
//            //Envia la imagen a la pantalla
//            origen = e.target; //objeto FileReader
//            //Prepara la información sobre la imagen
//            tipo = window.obtenerTipoMIME(origen.result.substring(0, 30));
//            jQuery('#infoNombre').text(Archivos[0].name + ' (Tipo: ' + tipo + ')');
//            jQuery('#infoTamaño').text('Tamaño: ' + e.total + ' bytes');
//            //Si el tipo de archivo es válido lo muestra, 
//            //sino muestra un mensaje 
//            if (tipo !== 'image/jpeg' && tipo !== 'image/png' && tipo !== 'image/gif') {
//                jQuery('#vistaPrevia').attr('src', window.imagenVacia);
//                alert('El formato de imagen no es válido: debe seleccionar una imagen JPG, PNG o GIF.');
//            } else {
//                jQuery('#vistaPrevia').attr('src', origen.result);
//                window.obtenerMedidas();
//            }
//
//        };
//        Lector.onerror = function (e) {
//            console.log(e);
//        };
//        Lector.readAsDataURL(Archivos[0]);
//    } else {
//        var objeto = jQuery("#form\\:foto");
//        objeto.replaceWith(objeto.val('').clone());
//        jQuery('#vistaPrevia').attr('src', window.imagenVacia);
//        jQuery('#infoNombre').text('[Seleccione una imagen]');
//        jQuery('#infoTamaño').text('');
//    }
//    ;
//};
////Lee el tipo MIME de la cabecera de la imagen
//window.obtenerTipoMIME = function obtenerTipoMIME(cabecera) {
//    return cabecera.replace(/data:([^;]+).*/, '\$1');
//};
////Obtiene las medidas de la imagen y las agrega a la 
////etiqueta infoTamaño
//window.obtenerMedidas = function obtenerMedidas() {
//    jQuery('<img/>').bind('load', function (e) {
//
//        var tamaño = jQuery('#infoTamaño').text() + '; Medidas: ' + this.width + 'x' + this.height;
//        jQuery('#infoTamaño').text(tamaño);
//    }).attr('src', jQuery('#vistaPrevia').attr('src'));
//};
//$(document).ready(function () {
////Cargamos la imagen "vacía" que actuará como Placeholder
//    jQuery('#vistaPrevia').attr('src', window.imagenVacia);
//    //El input del archivo lo vigilamos con un "delegado"
//    jQuery("#form\\:foto").change(function (e) {
//        window.mostrarVistaPrevia();
//    });
//    //El botón Cancelar lo vigilamos normalmente
//    jQuery('#cancelar').on('click', function (e) {
//        var objeto = jQuery("#form\\:foto");
//        objeto.val('');
//        jQuery('#vistaPrevia').attr('src', window.imagenVacia);
//        jQuery('#infoNombre').text('[Seleccione una imagen]');
//        jQuery('#infoTamaño').text('');
//    });
//});


$("#form\\:foto").on('change', function () {
    //Get count of selected files
    var countFiles = $(this)[0].files.length;
    var imgPath = $(this)[0].value;
    var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
    var image_holder = $("#marcoVistaPrevia");
    image_holder.empty();
    if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
        if (typeof (FileReader) != "undefined") {
            //loop for each file selected for uploaded.
            for (var i = 0; i < countFiles; i++)
            {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $("<img />", {
                        "id": "vistaPrevia",
                        "src": e.target.result,
                        "class": "thumb-image"
                    }).appendTo(image_holder);
                }
                image_holder.show();
                reader.readAsDataURL($(this)[0].files[i]);
            }
        } else {
            alert("This browser does not support FileReader.");
        }
    } else {
        alert("Pls select only images");
    }
});

                    