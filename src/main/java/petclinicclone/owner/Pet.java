package petclinicclone.owner;

import org.springframework.format.annotation.DateTimeFormat;
import petclinicclone.Model.NamedEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "pets")                         // 강아지용 Table 생성
public class Pet extends NamedEntity {           // 마찬가지로 NamedEntity (column 넣어둠) 상속했으니, 자동 들어감


    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")    // 패턴 정해주기
    private LocalDate birthDate;               //datatype - LocalDate 날짜

                           //category 는 많은 book 을 가짐 -> category 입장에서 one to many
                          // type 은    많은   pet 을 가짐 -> pet 입장에서 type 은 many to one (많은게 나한테 온다)
    @ManyToOne                                // Table - Table 연관관계 매핑
    @JoinColumn(name = "type_id")             // @JoinColumn <- foreign 키 매핑
    private PetType type;                     // "필드명_참조하는테이블(pets)의 기본key(@Id)Column명"

//근데 왜 pet type 은 그냥 바로 column 으로 안넣지? <보충>
// 이게 사실상 Column 으로 넣는게 거의 객체다. named Entity 도 객체, PetType 도 객체, Visit 도 객체.
// BirthDate 도 LocalDate 라는 객체.
// 근데 Owner 에서 Column  집어넣을 때는, city 나 address 같은 건, private String city; 로 선언하면서 바로 Column 에 넣었는데
// 여기서도 Private String petType; 해서 넣으면 Pet table 에 PetType 이라는 행이 추가되긴 할거다.
// 그런데 굳이 빈 PetType class 를 만들고 객체로 해서 넣는 것과의 차이는?
    //  즉 String 으로 넣느냐 VS 객체로 넣느냐 차이. String 으로 넣었으면, PetType, Visit 등을 객체로 이용하지 못한다.
    //  address, city 등은 한번 그냥 쓰고 말거라서 String 으로 쓰고 버려도 된다.

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)       //cascade - 연관관계 entity 매핑할때 전이도 설정
    @JoinColumn(name = "pet_id")                                        // fetch - 로딩 타입 EAGER / LAZY
    @OrderBy("visit_data ASC")                      //@OrderBy("Column명 ASC/DESC") 오름차순 내림차순
    private Set<Visit> visits = new LinkedHashSet<>();         //visit - date, description 왜 Set으로?
                                                              // 왜 LinkedHashSet?  -> 순서대로 출력

    public Set<Visit> getVisits() {            // Set 을 Super 인 Collection<Visit> 으로 함
        return visits;
    }

    public void addVisits(Visit visit){
        getVisits().add(visit);
    }

    // List 나 Set 같은 경우, getter 는 그대로 써서, List 등에서 꺼내서 쓸 수 있으나,
    // Setter 는 애초에 Set, List 형태로 Setter 를 사용할  수가 없다. 여기에 data 집어 넣을 땐, .add 로 넣었었다.
    // 마찬가지로 .add() 로  setter 를 대신하는 거다. (값을 넣어주는 것)



}
