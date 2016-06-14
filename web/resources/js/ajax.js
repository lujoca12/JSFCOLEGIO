/*  Mostrar el cuadro de diálogo*/
            


function mostrarCuadroDialogoPersonalizado(NombreContenedor)
{
   var MiContenedor="#"+NombreContenedor;
   var jQueryDialog1Opts =
   {
      width: 280,
      height: 100,
      position: { my: 'center', at: 'center', of: window },
      resizable: true,
      draggable: true,
      closeOnEscape: true,
      autoOpen: true
   };
   $(MiContenedor).dialog(jQueryDialog1Opts); 
}



// Esto es para cargar una página sin parámetros....
function CargarPaginaInterna(NombreContenedor,nombrePagina)
{
    $("#"+NombreContenedor).html('<div><center><img class=\"imgAjax\" src="../resources/img/ajax-loader.gif"/></center></div>');
    $("#"+NombreContenedor).load(nombrePagina+'.xhtml');
}
function CargarPaginaExterna(NombreContenedor,nombreCarpeta,nombrePagina)
{
    $("#"+NombreContenedor).html('<div><center><img class=\"imgAjax\" src="../resources/img/ajax-loader.gif"/></center></div>');
    $("#"+NombreContenedor).load('../'+nombreCarpeta+'/'+nombrePagina+'.xhtml');
}
function CargarPaginaURL(NombreContenedor,ruta)
{
    $("#"+NombreContenedor).html('<div><center><img class=\"imgAjax\" src="../resources/img/ajax-loader.gif"/></center></div>');
    $("#"+NombreContenedor).load(ruta+'.xhtml');
}

function isNumberKey(evt)
 {
        if (event.keyCode == 13) { test(); return false; }
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (!(charCode >= 48 && charCode <= 57) && charCode != 8 || charCode == 32){
            return false;
	}
         return true;
}

function soloNumeros(evt)
 {
     
        if (event.keyCode == 13 ) { test(); return false; }
        
         var charCode = (evt.which) ? evt.which : event.keyCode
         
         if (!(charCode >= 48 && charCode <= 57) && charCode != 8 || charCode == 32){
            return false;
	}
         return true;
}

function restablecer(e){
    if(e.keyCode == 8 || e.keyCode == 46) {
        test(); return false;
    }
}
function restablecerMm(e){
    if(e.keyCode == 46) {
        test1(); return false;
    }
}
    var validarNum = function (event, _float) {
    event = event || window.event;
    var charCode = event.keyCode || event.which;
    var first = (charCode <= 57 && charCode >= 48);
    if (_float) {
        var element = event.srcElement || event.target;
        return first || (element.value.indexOf('.') == -1 ? charCode == 46 : false);
    }
    return first;
}


function validarCedulaUsuarios() {
        
        var cedula = $(".validarCedula").val();
        
        var cedulaCorrecta = false;
        //alert(cedula);
        if (cedula.length == 10) {
            
            var tercerDig = cedula.charAt(2);
            if (tercerDig < 6) {
                var coefValidacion = [2, 1, 2, 1, 2, 1, 2, 1, 2];
                var verificador = cedula.charAt(9);
                var suma = 0;
                var valor = 0;
                for (var i = 0; i < coefValidacion.length; i++) {
                    valor = cedula.charAt(i) * coefValidacion[i];
                    suma = valor >= 10 ? suma + (valor - 9) : suma + valor;
                }
                var resultado = suma >= 10 ? (suma % 10) != 0 ? 10 - (suma % 10) : (suma % 10) : suma;
                if (resultado == verificador) {
                    cedulaCorrecta = true;
                }
            } else {
                cedulaCorrecta = false;
            }
        } else {
            cedulaCorrecta = false;
        }
        if (!cedulaCorrecta) {
            
            alert('Número de cédula no válido');
            $(".validarCedula").val("");
            $(".validarCedula").focus();
        }
    
}
;


    
            

