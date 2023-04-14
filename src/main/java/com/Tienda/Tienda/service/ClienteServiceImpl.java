package com.tienda.tienda.service;

import com.tienda.tienda.dao.ClienteDao;
import com.tienda.tienda.dao.CreditoDao;
import com.tienda.tienda.domain.Cliente;
import com.tienda.tienda.domain.Credito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    //Se crea en tiempo de ejecución si aún no se ha creado...
    @Autowired
    private ClienteDao clienteDao;
    
   @Autowired
    private CreditoDao creditoDao;

    
    @Override
    @Transactional(readOnly=true)
    public List<Cliente> getClientes() {
       return(List<Cliente>)clienteDao.findAll();
        
    }    
       

    @Override
    @Transactional
    public void save(Cliente cliente) {
        Credito credito = cliente.getCredito();
        credito =creditoDao.save(credito);
        cliente.setCredito(credito);
        
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
    clienteDao.delete(cliente);
      
    }

    @Override
    @Transactional(readOnly=true)
    public Cliente getCliente(Cliente cliente) {
     return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

    @Override
    public Cliente getByApellidos(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
