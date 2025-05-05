package com.minhadose.demo.repository;

import com.minhadose.demo.model.ContactModel;
import org.springframework.stereotype.Repository; 
import java.util.List;



@Repository
public class ContactRepository {

    public List<ContactModel> findAll() {
        
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public Object findById(Long id) {
       
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public ContactModel save(ContactModel existingContact) {
      
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public void deleteById(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }
    
}
