/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author aluno
 */
public interface InterfaceService<T> {

    public abstract void Criar(T objeto);

    public abstract T Carregar(int id);

    public abstract List<T> Carregar(String atributo, String valor);

    public abstract void Atualizar(T objeto);

    public abstract void Apagar(T objeto);

}
