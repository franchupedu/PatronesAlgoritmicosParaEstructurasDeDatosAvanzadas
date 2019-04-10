package dependencyInjection;

import java.util.List;

@Component
public class Rueda {
	 @Injected(count=6)
	 private List<Tuerca> tuercas;

}
