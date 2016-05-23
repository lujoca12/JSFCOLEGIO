/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author chiti
 */
@Named(value = "validadorCedulaBean")
@ViewScoped
public class validadorCedulaBean implements Serializable{

    private String cedOpas = "cedula";

    public String getCedOpas() {
        return cedOpas;
    }

    public void setCedOpas(String cedOpas) {
        this.cedOpas = cedOpas;
    }

    /**
     * Creates a new instance of validadorCedulaBean
     */
    public validadorCedulaBean() {
    }

    public boolean validadorDeCedula() {
        boolean cedulaCorrecta = false;
        String cedula = cedOpas;
        try {
            if (cedOpas.equals("cedula")) {

                if (cedula.length() == 10) // ConstantesApp.LongitudCedula
                {
                    int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                    if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                        int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                        int verificador = Integer.parseInt(cedula.substring(9, 10));
                        int suma = 0;
                        int digito = 0;
                        for (int i = 0; i < (cedula.length() - 1); i++) {
                            digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                            suma += ((digito % 10) + (digito / 10));
                        }

                        if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                            cedulaCorrecta = true;
                        } else if ((10 - (suma % 10)) == verificador) {
                            cedulaCorrecta = true;
                        } else {
                            cedulaCorrecta = false;
                        }
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

}
