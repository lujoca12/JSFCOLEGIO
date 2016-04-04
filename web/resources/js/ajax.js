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
//Seleccion de nivel padre e hijo
//function cambioNivel(){
//    if($("#cboNivel").val()== "0"){
//        $("#contHijo").css("display","none");
//        $("#contHijo1").css("display","none");
//        $("#contPadre label").remove();
//        $("#contPadre div").remove();
//        $("#contPadre").append("<label class=\"col-sm-2 col-sm-2 control-label\">Descripciòn</label>\n"+
//                      "<div class=\"col-sm-10\">\n"+
//                          "<input type=\"text\" name=\"txtPadreNombre\" id=\"txtPadreNombre\" class=\"form-control\">\n"+
//                      "</div>");
//    }else{
//        $("#contPadre label").remove();
//        $("#contPadre div").remove();
//        $("#contHijo").css("display","block");
//        $("#contHijo1").css("display","block");
//        //Cargar Padres
//        $.ajax({
//            url: "../obtenerPermisoPadres.dbo",
//            dataType: "text",
//            data: {
//                codigo:$("#cboNivel").val()
//            },//reques evia el parametro que digito
//            success: function (data) {
//                var str="";
//                str = "<label class=\"col-sm-2 col-sm-2 control-label\">Padre</label>\n" +
//"                        <div class=\"col-xs-5 selectContainer\">\n" +
//"                            <select class=\"form-control\" name=\"cboPadre\" id=\"cboPadre\">\n";
//                var parsed = JSON.parse(data);
//                for (var i = 0; i < parsed.length; i++) {
//                         str += "<option value=\""+parsed[i].id+"\">"+parsed[i].nombre+"</option>\n"; 
//                      }
//                    if(parsed.length <= 0)
//                        str +="<option value=\"-1\">(No hay datos)</option>\n"; 
//                        
//                        str +="</select>\n" +
//"                        </div>";
//                         $("#contPadre").append(str);
//                           // response(parsed);
//                        },error: function (message) {
//                        response([]);
//                    }
//                });
//    }
//}
//function cambioUsuario(){
//    //obtener_arbol_permisos.dbo
//    var _estado = false;
//    $("#contArbolPermisos").html('<div><center><img class=\"imgAjax\" src="../img/ajax-loader.gif"/></center></div>');
//    if($("#cboUsuario").val() != "-1"){
//        $.ajax({
//            url: "../obtener_arbol_permisos.dbo",
//            dataType: "text",
//            data: {
//                codigo:$("#cboUsuario").val()
//            },//reques evia el parametro que digito
//            success: function (data) {
//                var str="";
//                $("#contArbolPermisos div").remove();
//                var parsed = JSON.parse(data);
//                for (var i = 0; i < parsed.length; i++) {
//                    if(parsed[i].padre == 0){
//                        str += "<div class=\"panel-heading\">\n" +
//"                                            <input class=\""+parsed[i].id+"\" type=\"checkbox\" onchange=\"seleccionPardre("+parsed[i].id+");\" name=\"chk\" id=\"chk\"  value=\""+parsed[i].id+"\">\n" +
//"                                            <a class=\"panel-title collapsed menu-collapse\" data-toggle=\"collapse\" data-parent=\"#panel-izquierdo\" href=\"#panel-element-"+i+"\">\n" +
//"                                                 "+parsed[i].nombre+"    <span class=\"glyphicon glyphicon-collapse-down icon-right\"></span></a>\n" +
//"                                    </div>\n";
//                        str += "<div id=\"panel-element-"+i+"\" class=\"panel-collapse collapse\">\n";
//                    }
//                         for (var j = 0; j < parsed.length; j++) {
//                             if(parsed[i].id == parsed[j].padre){
//                                 if(parsed[j].estado == 10){
//                                    str +="<div class=\"panel-body icon-right\">\n" +
//"                                                <input class=\""+parsed[i].id+"\" type=\"checkbox\" onchange=\"seleccionHijo("+parsed[i].id+",this);\" checked=\"true\" name=\"chk id=\"chk\"  value=\""+parsed[j].id+"\"> <a class=\"cursor\">"+parsed[j].nombre+"</a>\n" +
//"                                            </div>\n";
//                                 }
//                                 else{
//                                     str +="<div class=\"panel-body icon-right\">\n" +
//"                                                <input class=\""+parsed[i].id+"\" type=\"checkbox\" onchange=\"seleccionHijo("+parsed[i].id+",this);\" name=\"chk id=\"chk\"  value=\""+parsed[j].id+"\"> <a class=\"cursor\">"+parsed[j].nombre+"</a>\n" +
//"                                            </div>\n";
//                                 }
//                             }
//                         }
//                         str += "</div>";  
//                      }
//                    if(parsed.length <= 0){
//                        //str +="<option value=\"-1\">(No hay datos)</option>\n"; 
//                    }
//                        
//                         $("#contArbolPermisos").append(str);
//                           // response(parsed);
//                        },error: function (message) {
//                        response([]);
//                    }
//                });
//    }else{
//        $("#contArbolPermisos div").remove();
//    }
//    
//}
////Funcion para seleccionar padre y de una se asignen permisos para hijo y viceversa
//function seleccionPardre(id){
//    
//    if($( "."+id).length>0){
//        //Padre
//        guardarPermisoUsuarios($( "."+id)[0].value,1);
//        for (var i = 0; i < $( "."+id).length-1; i++) {
//            
//            if($( "."+id)[0].checked){
//                $( "."+id)[i+1].checked= true;
//                guardarPermisoUsuarios($( "."+id)[i+1].value,1);
//            }
//            else{
//                $( "."+id)[i+1].checked= false;
//                guardarPermisoUsuarios($( "."+id)[i+1].value,0);
//            }
//        }
//    }
//}
////Volveremos
////Falta Guardar Padre
//function seleccionHijo(id,estado){
//   var _estado = "";
//   //Padre
//   guardarPermisoUsuarios($( "."+id)[0].value,1);
//   if(estado.checked)
//        guardarPermisoUsuarios(estado.value,1);
//    else
//        guardarPermisoUsuarios(estado.value,0);
//}
////Metodo para asignar los permisos a los usuarios
//function guardarPermisoUsuarios(id,estado){
//    $.ajax({
//            url: "../registrarPermisosxUsuarios.dbo",
//            dataType: "text",
//            data: {
//                permisoId:id,
//                permisoUs:$("#cboUsuario").val(),
//                estado:estado
//            },//reques evia el parametro que digito
//            success: function (data) {
//                //Metodo paa cargar el menu izquiero del panel de administracion
//                cargarMenuNavUsuario();
//                
//            }
//             });
//}
//
//
//function registrarPadre(){
//    
//    var nombre="";
//    var codigo="";
//    if($("#cboNivel").val() == 0){
//        nombre = $("#txtPadreNombre").val();
//        codigo = $("#cboNivel").val();
//    }else{
//        nombre = $("#txtHijoNombre").val();
//        codigo = $("#cboPadre").val();
//    }
//    $.ajax({
//            url: "../registrarPermisos.dbo",
//            dataType: "text",
//            data: {
//                codigo:codigo,//Nivel
//                orden: $("#cboOrden").val(),//Orden
//                nombre: nombre,
//                contenedor: $("#txtConten").val(),
//                ruta: $("#txtForm").val()
//            },//reques evia el parametro que digito
//            success: function (data) {
//                           if(data.toString() === "ok"){
//                               alert("Error al ingresar Datos");
//                           }else{
//                               alert("Datos Ingresados Correctamente");
//                               $("#txtPadreNombre").val("");
//                               $("#txtHijoNombre").val("");
//                               $("#txtConten").val("");
//                               $("#txtForm").val("");
//                               cambioUsuario();
//                               cargarMenuNavUsuario;
//                           }
//                            
//                        },error: function (message) {
//                        response([]);
//                    }
//                });
//    cargarMenuNavUsuario();
//}
//
////Funcion para cargar el menu de navegacion izquierdo del usuario
//function cargarMenuNavUsuario(){
//    //obtener_arbol_permisos.dbo
//    var band = false;
//    //$("#cargarMenuUsuarios").html('<div><center><img class=\"imgAjax\" src="../img/ajax-loader.gif"/></center></div>');
//        $.ajax({
//            url: "../obtenerMenuNavUsuario.dbo",
//            dataType: "text",
//            data: {
//                
//            },//reques evia el parametro que digito
//            success: function (data) {
//                
//                var str1="";
//                $("#cargarMenuUsuarios div").remove();
//                $("#cargarMenuUsuarios ul").remove();
//                var parsed = JSON.parse(data);
//                for (var i = 0; i < parsed.length; i++) {
//                    if(parsed[i].padre == 0){
//                        band = false;
//                        for(var j = 0;j<parsed.length;j++){
//                            if(parsed[i].id == parsed[j].padre){
//                                band = true;
//                            }
//                        }
//                        if(band){
//                            
//                            str1 += "<ul class=\"sidebar-menu\">\n" +
//"                                        <li>\n" +
//"                                            <a class=\"panel-title collapsed menu-collapse\" data-toggle=\"collapse\" data-parent=\"#panel-izquierdo\" href=\"#panel-element"+i+"\">\n" +
//"                                                <!--<span class=\"glyphicon glyphicon-user icon-leftP\"></span>-->"+parsed[i].nombre+"<span class=\"glyphicon glyphicon-collapse-down icon-rightP\"></span></a>\n" +
//"                                        </li>\n" +
//"                                    </ul>\n" +
//"                                                <div id=\"panel-element"+i+"\" class=\"panel-collapse collapse\">";
//
//                                for(var j = 0;j<parsed.length;j++){
//                                    if(parsed[i].id == parsed[j].padre){
//                                        
//                                        band = true;
//                                        str1 += "<div class=\"panel-body\">\n" +
//"                                            <a class=\"cursor\" onclick=\""+parsed[j].urls+"\">"+parsed[j].nombre+"</a>\n" +
//"                                        </div>";
//                                }
//                            }
//                            str1 += "</div>";
//                        }
//                        
//                    }
//                         
//                  }
//                    if(parsed.length <= 0){
//                        //str +="<option value=\"-1\">(No hay datos)</option>\n"; 
//                    }
//                        
//                         $("#cargarMenuUsuarios").append(str1);
//                           // response(parsed);
//                        },error: function (message) {
//                        response([]);
//                    }
//                });
//}
//
//function editarPermiso(fila,valor,columna,idTxt){
//    $("#txtNombre"+idTxt).removeAttr('disabled');
//    $("#txtOrden"+idTxt).removeAttr('disabled');
//    $("#txtNombre"+idTxt).css('background-color','white');
//    $("#txtOrden"+idTxt).css('background-color','white');
//    
//    $("#txtNombre"+idTxt).focus();
//    
//    if($($("."+fila)[columna]).find("td")[2].innerHTML != "0"){
//        $("#txtUrls"+idTxt).removeAttr('disabled');
//    }
//    
//    $($("."+fila)[columna]).find("td")[5].innerHTML="<img class=\"imgAjaxTabla\" src='../img/ajax-loader.gif' /> Procesando";
//
//    $($("."+fila)[columna]).find("td")[5].innerHTML= "<button id=\"btnGuardar"+idTxt+"\" onclick=\"guardarEdicionPermiso('"+fila+"','"+valor+"','"+columna+"','"+idTxt+"')\" class=\"btn btn-default btn-xs\"><i class=\"fa fa-save\"></i></button>\n" +
//"                                       <button id=\"btnCancelarEdicion"+idTxt+"\" onclick=\"cancelarEdicionPermiso('"+fila+"','"+valor+"','"+columna+"','"+idTxt+"')\" class=\"btn btn-default btn-xs\"><i class=\"fa fa-recycle\"></i></button>";
//}
//
//function cancelarEdicionPermiso(fila,valor,columna,idTxt){
//        
//    $("#txtNombre"+idTxt).css('background-color','#ebf4f8');
//    $("#txtOrden"+idTxt).css('background-color','#ebf4f8');
//    $("#txtNombre"+idTxt).attr('disabled','disabled');
//    $("#txtOrden"+idTxt).attr('disabled','disabled');
//    
//    if($($("."+fila)[columna]).find("td")[2].innerHTML != "0"){
//        $("#txtUrls"+idTxt).attr('disabled','disabled');
//        $("#txtUrls"+idTxt).val($($($("."+fila)[columna]).find("td")[3].innerHTML).val());
//        $("#txtNombre"+idTxt).css('background-color','white');
//        $("#txtOrden"+idTxt).css('background-color','white');
//    }
//    
//    $($("."+fila)[columna]).find("td")[5].innerHTML="<img class=\"imgAjaxTabla\" src='../img/ajax-loader.gif' /> Procesando";
//    
//
//    $("#txtNombre"+idTxt).val($($($("."+fila)[columna]).find("td")[1].innerHTML).val());
//    
//    $($("."+fila)[columna]).find("td")[5].innerHTML= "<button id=\"btnEditar"+idTxt+"\" onclick=\"editarPermiso('"+fila+"','"+valor+"','"+columna+"','"+idTxt+"')\" class=\"btn btn-default btn-xs\"><i class=\"fa fa-pencil\"></i></button>";
//}
//function guardarEdicionPermiso(fila,valor,columna,idTxt){
//    $($("."+fila)[columna]).find("td")[5].innerHTML="<img class=\"imgAjaxTabla\" src='../img/ajax-loader.gif' /> Procesando";
//    var url="";
//    var padre = $("#txtNombre"+idTxt).val();
//    if($($("."+fila)[columna]).find("td")[2].innerHTML != "0"){
//        url = $("#txtUrls"+idTxt).val();
//        actualizarPermiso(valor,padre,url,$("#txtOrden"+idTxt).val());
//        $("#txtUrls"+idTxt).attr('disabled','disabled');
//        $("#txtNombre"+idTxt).css('background-color','#ebf4f8');
//        
//    }else{
//        actualizarPermiso(valor,padre,url,$("#txtOrden"+idTxt).val());
//    }
//    $("#txtOrden"+idTxt).css('background-color','#ebf4f8');
//    $("#txtOrden"+idTxt).attr('disabled','disabled');
//    $("#txtNombre"+idTxt).attr('disabled','disabled');
//    $("#txtNombre"+idTxt).css('background-color','#ebf4f8');
//    $($("."+fila)[columna]).find("td")[5].innerHTML= "<button id=\"btnEditar"+idTxt+"\" onclick=\"editarPermiso('"+fila+"','"+valor+"','"+columna+"','"+idTxt+"')\" class=\"btn btn-default btn-xs\"><i class=\"fa fa-pencil\"></i></button>";
//}
//function actualizarPermiso(id,padre,url,orden){
//    $.ajax({
//            url: "../actualizarPermisosDetalles.dbo",
//            dataType: "text",
//            data: {
//                id:id,
//                padre: padre,
//                urls: url,
//                orden: orden
//            },//reques evia el parametro que digito
//            success: function (data) {
//                cargarMenuNavUsuario();
//            }
//     });
//}
////Metodo que actua en la tabla de permisos cuando se selecciona eliminar
//function eliminarPermiso(fila,valor,posicion){
//    
//    //$($("."+fila)[columna]).find("td")[6].innerHTML="<img class=\"imgAjaxTabla\" src='../img/ajax-loader.gif' /> Procesando";
//    
//    if($($("."+fila)[posicion]).find("td")[2].innerHTML != "0"){
//        $($("."+fila)[posicion]).css("display","none");
//        eliminarPermisosTodosUsuarios(valor,0);
//    }else{
//        for (var i = $("."+fila).length-1; i >= 0; i--) {
//            eliminarPermisosTodosUsuarios($($("."+fila)[i]).find("td")[0].innerHTML,0);
//            //alert($($("."+fila)[i]).find("td")[0].innerHTML);
//            $($("."+fila)[i]).css("display","none");
//        }
//    }
//}
////Este metodo lo utilizo para eliminar todos los permisos
////de cualquier usuario
//function eliminarPermisosTodosUsuarios(id,estado){
//    
//    $.ajax({
//            url: "../eliminarPermisoTodosUsuarios.dbo",
//            dataType: "text",
//            data: {
//                permisoId:id,
//                estado:estado
//            },//reques evia el parametro que digito
//            success: function (data) {
//               cargarMenuNavUsuario();
//            }
//             });
//}
//
//function muestraGranDiv(){
//    $('.procesando button').remove();
//    var str= "<center><img src=\"img/loading.gif\" class=\"imgAjaxTabla\" /> Procesando.....</center>";
//    $('.procesando').append(str);
//}
//
//function tblEdicionPermisos(){
//    $("#contact-2").html("<div><center><img class=\"imgAjax\" src=\"../img/ajax-loader.gif\"/></center></div>");
//    $.ajax({
//            url: "../obtenerTblEdicionPermisos.dbo",
//            dataType: "text",
//            data: {
//            },//reques evia el parametro que digito
//            success: function (data) {
//                $("#contact-2 div").remove();
//                $('#contact-2').html(data);
//            }
//             });
//}
//
//function isNumberKey(evt)
// {
//         var charCode = (evt.which) ? evt.which : event.keyCode
//         if (!(charCode >= 48 && charCode <= 57) && charCode != 8 || charCode == 32){
//            return false;
//	}
//         return true;
//}