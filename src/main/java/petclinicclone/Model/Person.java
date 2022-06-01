package petclinicclone.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public class Person extends BaseEntity{                 //NamedEntity 부터 Person 까지 다 BaseEntitiy 상속중.
                                                       // id 값 밖에 없는데 그 KS 인가 그것 때문에 상속해서 쓰려는 듯

    // id 야 ks 값, 구분자로 그러려니 하는데 어짜피 first name last name 으로 도메인 entity 생성하는데
    // NamedEntity - 그냥 String name 인 도메인 entity 를 왜 또 따로 만들고 있어야 하는지 ??? 보충

    @Column(name = "first_name")                       //Column 으로 table 하고 이름 다를 때 준다, 보충 필
    @NotEmpty                                         //NotEmpty 보충 필
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
