package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    public Attachment findByItemIdAndName(long itemId, String name);
}


