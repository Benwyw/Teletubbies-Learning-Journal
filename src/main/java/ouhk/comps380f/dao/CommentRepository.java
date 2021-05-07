/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

/**
 *
 * @author Ronaldtsai
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Comment;

/**
 *
 * @author Ronaldtsai
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{
    
    public Comment findByUsername(String name);
}