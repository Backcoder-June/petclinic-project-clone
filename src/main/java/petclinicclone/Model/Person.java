package petclinicclone.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass                       //BaseEntity, NamedEntity, Person 은 다 MappedSuperclass 가져다 쓸거니까
public class Person extends BaseEntity{                //NamedEntity 부터 Person 까지 다 BaseEntity 상속중.
                                                       // id 값을 PK로 상속해서 쓰려는 듯

    // NamedEntity 에서 String name 따로  여기서 firstName lastName 따로 만드는 이유 <보충>
    // 강아지이름이었다 NamedEntity  맞네 필요하네

    @Column(name = "first_name")                   //Column 으로 table 행 추가
    @NotEmpty                                      //@NotNull -  Null(불가) /(empty) ""(가능) /(blank) " "(가능)/
    private String firstName;                      // @NotEmpty - Null(불가) /(empty)"" (불가) /(blank) " " (가능)/
    @Column(name = "last_name")
    @NotEmpty                                      //사용자가 name 입력할때, 빈값으로 못만들게 하려고
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
