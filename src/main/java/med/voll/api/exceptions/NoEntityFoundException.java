package med.voll.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoEntityFoundException extends Exception{

    private String msg;

    public NoEntityFoundException(String msg){
        super();
        this.msg="No entity \""+msg+"\" found.";
    }
}
