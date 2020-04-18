package sh.locus.accessmanagement.request;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class CreateResourceRequest extends RequestDTO{

    @NonNull
    private String name;

    private LocalDateTime creationTime;

    public CreateResourceRequest(@NonNull String name) {
        this.name = name;
        this.creationTime = LocalDateTime.now();
    }
}
