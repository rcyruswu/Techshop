package com.tienda.tienda.service;

import com.tienda.tienda.dao.ArticuloDao;
import com.tienda.tienda.domain.Articulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ArticuloServiceImpl implements ArticuloService{
    
    //Se crea en tiempo de ejecución si aún no se ha creado...
    @Autowired
    private ArticuloDao articuloDao;

    @Override
    @Transactional(readOnly=true)
    public List<Articulo> getArticulos(boolean activos) {
        var lista=(List<Articulo>)articuloDao.findAll();
        if(activos){
            lista.removeIf(e -> !e.isActivo());
          }  
       
         return lista;
 
       }   
         
    @Override
    @Transactional
    public void save(Articulo articulo) {
        articuloDao.save(articulo);
    }
        
    @Override
    @Transactional
    public void delete(Articulo articulo) {
    articuloDao.delete(articulo);
      
    }

    @Override
    @Transactional(readOnly=true)
    public Articulo getArticulo(Articulo articulo) {
     return articuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }
    
}
