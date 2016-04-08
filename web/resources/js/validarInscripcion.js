/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.validator.addMethod("lettersonly", function (value, element) {
        return this.optional(element) || /^[a-z]+$/i.test(value);
    }, "Solo letras por favor!");
    $.validator.methods.email = function (value, element) {
        return this.optional(element) || /^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})/.test(value);
    };

//old email pattern [a-z]+@[a-z]+\.[a-z]+

//    var $validator = $("#form").validate({
//        rules: {
//            "form:cedPas": {
//                required: true,
//                digits: true,
//                maxlength: 10
//
//            },
//            "form:nombres": {
//                required: true,
//                lettersonly: true,
//                maxlength: 40
//            },
//            "form:apellidos": {
//                required: true,
//                lettersonly: true,
//                maxlength: 40
//            },
//            "form:fNac_input": {
//                required: true,
//            },
//            "form:email": {
//                required: true,
//                email: true,
//                maxlength: 45
//            },
//            "form:celular": {
//                required: true
//            }
//        }
//    });

    $('#rootwizard').bootstrapWizard({
        'tabClass': 'nav nav-pills',
        'onNext': function (tab, navigation, index) {
            var $valid = $("#form").valid();
            if (!$valid) {
                $validator.focusInvalid();
                return false;
            }
        }
    });
    

});
