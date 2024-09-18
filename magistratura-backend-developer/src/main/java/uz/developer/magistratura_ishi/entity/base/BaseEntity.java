package uz.developer.magistratura_ishi.entity.base;

import org.springframework.data.annotation.Id;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @jakarta.persistence.Id
    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass().getName() != obj.getClass().getName()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return Objects.equals(getIdentifier(), ((BaseEntity) obj).getIdentifier());
    }

    @Override
    public String toString() {
        return getIdentifier();
    }
}
