/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package pe.com.sistradoc.services;

import org.hibernate.service.spi.ServiceException;

import pe.com.sistradoc.dto.TramiteDTO;
import pe.com.sistradoc.utils.ValidateService;

public interface TramiteProcessRegister {

    public ValidateService registrarTramite(TramiteDTO tramiteDto) throws ServiceException;

}
