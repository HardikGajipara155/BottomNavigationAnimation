# BottomNavigation Animation 



1. Add maven { url 'https://jitpack.io' } in settings.gradle()

	

   		dependencyResolutionManagement {
		
			repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		
			repositories {
				mavenCentral()


  				maven { url 'https://jitpack.io' }

			}
		}
2. Add This Libary in build.gradle(.app)


       implementation 'com.github.HardikGajipara155:BottomNavigationAnimation:1.0.0'
