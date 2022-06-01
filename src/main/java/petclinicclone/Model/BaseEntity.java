package petclinicclone.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass                                 // 상속받는 subclass 들이 mapped 된다. Entity 역할. 자신은 X
public class BaseEntity implements Serializable {        //Serializable <<

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //public boolean isnew <  생성하는데 어디에 쓰려고 생성하는가
}
