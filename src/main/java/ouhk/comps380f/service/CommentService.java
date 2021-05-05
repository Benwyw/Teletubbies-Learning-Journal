/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.service;

import java.util.List;
import ouhk.comps380f.exception.CommentNotFound;
import ouhk.comps380f.model.Comment;

/**
 *
 * @author Ronaldtsai
 */
public interface CommentService {
    public Comment getComments(long id);
}

