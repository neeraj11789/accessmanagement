package sh.locus.accessmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResourcePermission {

    @Getter @Setter
    private Resource resource;

    @Getter @Setter
    private ActionType actionType;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        ResourcePermission rp = (ResourcePermission) obj;
        return (rp.getResource() == this.resource && rp.getActionType() == this.actionType);
    }

    @Override
    public int hashCode() {
        /**
         * @NOTE: Could use the ID identifier when using DAO
         * Using HashCode of the String Method
         */
        return this.resource.getId().hashCode();
    }
}