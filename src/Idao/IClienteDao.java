/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import Modelo.Cliente;
import java.util.List;


/**
 *
 * @author miguel
 */
public interface IClienteDao {
 
    public boolean guardar(Cliente cliente);
    public List<Cliente> listar();
    public boolean actualizar(Cliente cliente);
    public boolean eliminar(Cliente cliente);
    public Cliente getCliente(int clienteId);
    
}
