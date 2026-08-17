# Fix Jetpack Compose Preview Rendering Errors

The project currently suffers from Preview rendering failures on multiple screens (`LoginScreen`, `RegisterScreen`, `ForgotPasswordScreen`) because these composables attempt to initialize Firebase services (`FirebaseAuth`, `FirebaseDatabase`) during the composition phase. Firebase is not initialized in the Android Studio Preview environment, leading to `IllegalStateException`.

## User Review Required

> [!IMPORTANT]
> To fix the Preview rendering without changing the application's runtime behavior or UI, I will introduce a "Preview-safe" way to access Firebase services. This involves guarding the singleton initialization with `LocalInspectionMode.current`.

## Proposed Changes

### UI Screens

#### [MODIFY] [LoginScreen.kt](file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/authentication/LoginScreen.kt)
*   Guard `FirebaseAuth.getInstance()` with `LocalInspectionMode.current`.
*   Update usages of `auth` to handle the nullable type (safe calls), which is safe since these are only used in `onClick` handlers.

#### [MODIFY] [RegisterScreen.kt](file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/authentication/RegisterScreen.kt)
*   Guard `FirebaseAuth.getInstance()` and `FirebaseDatabase.getInstance()` with `LocalInspectionMode.current`.
*   Fix the compilation warning/error related to `FirebaseDatabase` and `addOnCompleteListener` if they persist after the preview fix.
*   Update usages of `auth` and `database` to handle nullable types.

#### [MODIFY] [ForgotPasswordScreen.kt](file:///C:/Users/kabir/StudioProjects/Mtaani/app/src/main/java/com/allan/mtaani/ui/screens/forgotpassword/ForgotPasswordScreen.kt)
*   Guard `FirebaseAuth.getInstance()` with `LocalInspectionMode.current`.
*   Update usages of `auth` to handle the nullable type.

## Verification Plan

### Automated Tests
*   Run `gradle_build` to ensure no regression in compilation.
*   Render Compose Previews for all three modified screens using `render_compose_preview`.

### Manual Verification
*   Verify that `HomeScreen`, `SplashScreen`, and `OnboardingScreens` still render correctly as they were not affected by this specific issue.
*   Confirm that the visual design of all screens remains identical.
