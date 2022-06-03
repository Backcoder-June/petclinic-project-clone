package petclinicclone.owner;

import org.springframework.format.annotation.DateTimeFormat;
import petclinicclone.Model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Table(name = "visits")
public class Visit extends BaseEntity {



    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;           //visit 날짜


    @NotEmpty
    private String description;       //visit 내용


    //Constructor -> .now 로 정의
    public Visit(){
        this.date = LocalDate.now();
    }


    //Getter Setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
