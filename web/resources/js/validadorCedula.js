/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

////<![CDATA[ 
//$(document).ready(function () {
//    $("#form\\:cedPas").focusout(function () {
//        var tipoD =$("input[name='form\\:tipoDoc']:checked").val();alert(tipoD);
//        if (tipoD=="cedula") {
//            var cedula = $("#form\\:cedPas").val();
//            var cedulaCorrecta = false;
//            if (cedula.length == 10) {
//                var tercerDig = cedula.charAt(2);
//                if (tercerDig < 6) {
//                    var coefValidacion = [2, 1, 2, 1, 2, 1, 2, 1, 2];
//                    var verificador = cedula.charAt(9);
//                    var suma = 0;
//                    var valor = 0;
//                    for (var i = 0; i < coefValidacion.length; i++) {
//                        valor = cedula.charAt(i) * coefValidacion[i];
//                        suma = valor >= 10 ? suma + (valor - 9) : suma + valor;
//                    }
//                    var resultado = suma >= 10 ? (suma % 10) != 0 ? 10 - (suma % 10) : (suma % 10) : suma;
//                    if (resultado == verificador) {
//                        cedulaCorrecta = true;
//                    }
//                } else {
//                    cedulaCorrecta = false;
//                }
//            } else {
//                cedulaCorrecta = false;
//            }
//            if (!cedulaCorrecta) {
//                alert('Número de cédula no válido');
//                $("#form\\:cedPas").val("");
//            } else {
//                alert('Número de cédula válido');
//            }
//        }
//    });
//});
////]]>
function validarCedula(){
                            var tipoD = $("input[name='form\\:tipoDoc']:checked").val();                            
                            if (tipoD == "cedula") {
                                var cedula = $("#form\\:cedPas").val();
                                var cedulaCorrecta = false;
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
                                    $("#form\\:cedPas").val("");
                                    $("#form\\:cedPas").focus();
                                } 
                            }
                        };