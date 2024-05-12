package Controlador.Categoria;

import Modelo.CRUDCategoria;

public class CategoriaControllerDelete {
    private final CRUDCategoria categoriaModelo;
    
    public CategoriaControllerDelete(){
        this.categoriaModelo = new CRUDCategoria();
    }
    
    public String eliminarCategoria(int idCategoria) {
        return categoriaModelo.eliminarCategoria(idCategoria);
    }  
}
