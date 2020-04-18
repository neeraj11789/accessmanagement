package sh.locus.accessmanagement.request;

import java.io.Serializable;
import java.util.UUID;

public class RequestDTO implements Serializable {

    String requestId = UUID.randomUUID().toString();

}
