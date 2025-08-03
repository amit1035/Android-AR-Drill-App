# AR Drill Placement App for Android

## 🎯 Overview
This is a minimal AR application that allows users to select a drill from a list and place drill markers in AR by tapping on detected horizontal planes (floor/ground).

## 📱 Features

### ✅ Completed Features
1. **Drill Selection UI**
   - Dropdown list with 3 different drills (Basic, Advanced, Expert)
   - Modern Material Design interface with cards and smooth animations

2. **Drill Detail Pages**
   - Individual pages for each drill with dummy data
   - Display sections: Description, Tips, and visual representation
   - Different content and colors for each drill type

3. **AR Functionality**
   - Real-time camera feed with ARCore integration
   - Horizontal plane detection (floor/ground)
   - Tap-to-place drill markers (colored 3D cubes)
   - Different colored markers for different drills:
     - Drill 1 (Basic): Red cube
     - Drill 2 (Advanced): Green cube  
     - Drill 3 (Expert): Blue cube
   - Only one marker at a time (replaces previous when new one is placed)

4. **Permissions & Error Handling**
   - Camera permission requests
   - ARCore availability checks
   - User-friendly error messages
   - Graceful handling of unsupported devices

## 🏗️ Architecture

### UI Flow
```
MainActivity (Drill Selection)
    ↓
DrillDetailActivity (Drill Info)
    ↓
ARActivity (AR Scene with Tap-to-Place)
```

### Key Components
- **MainActivity**: Drill selection with spinner
- **DrillDetailActivity**: Detailed drill information display
- **ARActivity**: AR scene with plane detection and object placement

## 🛠️ Technical Stack
- **Language**: Kotlin
- **AR Framework**: Google ARCore
- **UI**: Android Views (Material Design)
- **3D Rendering**: OpenGL ES 2.0
- **Minimum SDK**: API 26 (Android 8.0)
- **Target SDK**: API 34 (Android 14)

## 📋 Prerequisites
- Android Studio Arctic Fox or later
- Android device with ARCore support
- Camera permission
- Minimum Android 8.0 (API 26)

## 🚀 How to Run

### Option 1: Using Android Studio
1. **Clone/Download** the project
2. **Open** in Android Studio
3. **Sync** Gradle files
4. **Connect** an ARCore-supported Android device
5. **Run** the app (Ctrl+R or click Run button)

### Option 2: Install APK
1. **Build** the project in Android Studio
2. **Generate APK**: Build → Build Bundle(s)/APK(s) → Build APK(s)
3. **Install** on ARCore-supported device
4. **Grant** camera permission when prompted

## 📱 Usage Instructions

### Step 1: Select Drill
- Open the app
- Choose from 3 available drills using the dropdown
- Tap "View Drill Details"

### Step 2: Review Drill Information  
- Read drill description and tips
- Tap "🚀 Start AR Drill" to begin AR experience

### Step 3: Place Drill Marker in AR
- **Allow camera permission** when prompted
- **Move your device** slowly to detect horizontal surfaces
- **Wait** for plane detection (floor/ground surfaces will be detected)
- **Tap** on detected surface to place drill marker
- **Tap elsewhere** to move the marker to a new location

## 🎨 Drill Types & Colors

| Drill Type | Color | Description |
|------------|--------|-------------|
| Drill 1 - Basic | 🔴 Red | Beginner-friendly drill with basic movements |
| Drill 2 - Advanced | 🟢 Green | Intermediate drill with complex movements |  
| Drill 3 - Expert | 🔵 Blue | Advanced drill for experienced users |

## 🔧 Project Structure
```
app/src/main/
├── java/com/example/arplacementappforandroid/
│   ├── MainActivity.kt          # Drill selection screen
│   ├── DrillDetailActivity.kt   # Drill information display  
│   └── ARActivity.kt            # AR scene with object placement
├── res/
│   ├── layout/
│   │   ├── activity_main.xml         # Main selection UI
│   │   ├── activity_drill_detail.xml # Drill detail UI
│   │   └── activity_ar.xml          # AR scene UI
│   ├── drawable/
│   │   └── button_primary.xml       # Custom button styling
│   ├── values/
│   │   └── strings.xml              # App strings
│   └── xml/
└── AndroidManifest.xml              # App permissions & activities
```

## ⚠️ Known Limitations
1. **Device Compatibility**: Requires ARCore-supported devices
2. **Lighting Conditions**: Works best in well-lit environments  
3. **Surface Detection**: Needs textured surfaces for better plane detection
4. **3D Models**: Uses simple colored cubes instead of complex 3D drill models
5. **Network**: No network features implemented (offline app)

## 🐛 Troubleshooting

### AR Not Working?
- **Check device compatibility**: [ARCore supported devices](https://developers.google.com/ar/devices)
- **Update ARCore**: Install/update from Google Play Store
- **Camera permission**: Ensure camera permission is granted
- **Lighting**: Use in well-lit environment
- **Surface**: Point camera at textured horizontal surfaces

### App Crashes?
- **Clear app data** and restart
- **Reinstall ARCore** from Play Store  
- **Check Android version** (minimum API 26)
- **Free up storage space**

### Build Issues?
- **Sync Gradle** files
- **Clean and rebuild** project
- **Check SDK versions** match requirements
- **Update Android Studio** to latest version

## 📚 Learning Resources
- [ARCore Developer Guide](https://developers.google.com/ar)
- [Android OpenGL ES Guide](https://developer.android.com/guide/topics/graphics/opengl)
- [Material Design Guidelines](https://material.io/design)

## 🔮 Future Enhancements
- [ ] 3D drill models instead of colored cubes
- [ ] Multiple marker placement
- [ ] Drill animation sequences  
- [ ] Social sharing of AR scenes
- [ ] Cloud anchor support for shared AR experiences
- [ ] Voice commands for hands-free operation

## 📄 License
This project is created for educational purposes as part of an AR development assignment.

---
**Built with ❤️ using ARCore and Android**
