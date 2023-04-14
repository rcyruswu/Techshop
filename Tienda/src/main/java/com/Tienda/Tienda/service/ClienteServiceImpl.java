package com.tienda.tienda.service;

import com.tienda.tienda.dao.ClienteDao;
import com.tienda.tienda.domain.Cliente;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClienteServiceImpl implements ClienteService{

     //Se crea en tiempo de ejecución si aún no se ha creado...
    @Autowired
    private ClienteDao clienteDao;
    
   @Override
   @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
       return(List<Cliente>) clienteDao.findAll();
    }
    

    @Override
    @Transactional
    public void save(Cliente cliente) {
       clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }
    
    
}

    

