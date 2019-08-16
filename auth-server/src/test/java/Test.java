import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("W@u&Jl2OPD"));
		System.out.println(new BCryptPasswordEncoder().encode("We@lthCh0ng"));

	}

}
