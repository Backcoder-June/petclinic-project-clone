package petclinicclone.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass                                        // 상속받는 subclass 들에게 mapping 정보 제공
public class BaseEntity implements Serializable {        // Serializable << Byte 로 만들기

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return this.id == null;}
    // DB에 저장되지 않은 새로운 엔티티를 어떻게 식별할지 overriding 해서 사용
    // pet.isNew() -> PK(id) 를 이용해서 pet 에 id 가 없으면(=null) true / 있으면 false

}
