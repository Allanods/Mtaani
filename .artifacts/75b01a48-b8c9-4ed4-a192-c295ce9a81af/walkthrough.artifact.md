# Fix Compose Preview Rendering Errors

I have fixed the rendering errors in the Compose Previews across the project. The root cause was the initialization of Firebase services during composition, which is not supported in the Preview environment.

## Changes Made

### UI Screens

#### [LoginScreen.kt](file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/authentication/LoginScreen.kt)
*   Introduced `LocalInspectionMode.current` to guard `FirebaseAuth.getInstance()`.
*   Updated `onClick` handlers to use safe calls (`?.`) on the nullable `auth` object.

#### [RegisterScreen.kt](file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/authentication/RegisterScreen.kt)
*   Applied the same preview-safe pattern for both `FirebaseAuth` and `FirebaseDatabase`.
*   Ensured all runtime interactions (saving to database, updating profile) are safely handled.

#### [ForgotPasswordScreen.kt](file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/forgotpassword/ForgotPasswordScreen.kt)
*   Made the screen preview-safe by guarding the `FirebaseAuth` instance and using safe calls in the reset link logic.

## Verification Results

### Automated Tests
*   **Build**: Successfully built the project using `./gradlew app:assembleDebug`.
*   **Previews**: Verified that all affected screens now render correctly in the Compose Preview tool.

### Manual Verification
*   Confirmed that screens like `HomeScreen` and `SplashScreen` remain fully functional and visually unchanged.
*   The application's runtime behavior remains identical, as the Firebase services are only null during Preview inspection.

render_diffs(file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/authentication/LoginScreen.kt)
render_diffs(file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/authentication/RegisterScreen.kt)
render_diffs(file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/forgotpassword/ForgotPasswordScreen.kt)
