/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.CommentRepository;
import ouhk.comps380f.model.Comment;

/**
 *
 * @author Ronaldtsai
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Resource
    private CommentRepository commentRepo;
    
    @Override
    @Transactional
    public Comment getComments(long id) {
        return commentRepo.findById(id).orElse(null);
    }
}
