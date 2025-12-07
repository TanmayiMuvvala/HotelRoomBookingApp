<<<<<<< HEAD
# 🏨 Hotel Room Booking App

A professional Android hotel booking application with user authentication, room management, service booking, and profile management.

## ✨ Features

### 🔐 User Management
- **User Registration** - Create new accounts with username, password, full name, email, and phone
- **Secure Login** - Database-backed authentication with session management
- **Profile Management** - View and edit user profile information
- **Auto-login** - Remembers logged-in users for seamless experience

### 🛏️ Room Booking
- **Browse Rooms** - View all available hotel rooms with details
- **Room Details** - See comprehensive information about each room
- **Smart Booking** - Date picker for check-in/check-out with automatic price calculation
- **Real-time Pricing** - Calculates total cost based on number of nights

### 🛎️ Hotel Services
- **Room Cleaning** - Request housekeeping services
- **Laundry Service** - Book laundry with default pricing
- **Food Delivery** - Order room service

### 📋 Booking Management
- **Booking History** - View all past room and service bookings
- **Detailed Records** - See customer name, dates, prices, and service details

### ⚙️ Settings & Profile
- **User Profile** - Update personal information
- **Settings Menu** - Access app settings and information
- **About Section** - App version and information
- **Logout** - Secure session termination

### 🎨 UI/UX Enhancements
- **Material Design** - Modern Material Components theme
- **Smooth Transitions** - Fade and slide animations between screens
- **Responsive Layouts** - Works on phones and tablets
- **Professional UI** - Card-based layouts with proper spacing and colors

## 🛠️ Tech Stack

- **Language**: Java
- **Database**: SQLite with comprehensive schema
- **UI Framework**: Material Design Components
- **Architecture**: Activity-based with DatabaseHelper pattern
- **Min SDK**: 23 (Android 6.0)
- **Target SDK**: 36

## 📱 App Structure

```
├── Activities
│   ├── SplashActivity          # Entry point with auto-login check
│   ├── LoginActivity           # User authentication
│   ├── RegisterActivity        # New user registration
│   ├── DashboardActivity       # Main navigation hub
│   ├── ProfileActivity         # User profile management
│   ├── SettingsActivity        # App settings
│   ├── RoomsListActivity       # Browse available rooms
│   ├── RoomDetailsActivity     # Room information
│   ├── BookRoomActivity        # Room booking with date picker
│   ├── ServicesListActivity    # Hotel services menu
│   ├── BookServiceActivity     # Service booking
│   └── BookingHistoryActivity  # View all bookings
├── Database
│   └── DatabaseHelper          # SQLite database management
├── Models
│   ├── RoomModel              # Room data structure
│   ├── RoomBookingModel       # Room booking data
│   └── ServiceBookingModel    # Service booking data
└── Adapters
    └── RoomAdapter            # RecyclerView adapter for rooms
```

## 🗄️ Database Schema

### Users Table
- `user_id` (Primary Key)
- `username` (Unique)
- `password`
- `full_name`
- `email`
- `phone`

### Rooms Table
- `room_id` (Primary Key)
- `room_number`
- `room_type`
- `price`
- `description`
- `availability`
- `image_url`

### RoomBookings Table
- `booking_id` (Primary Key)
- `room_id` (Foreign Key)
- `customer_name`
- `checkin_date`
- `checkout_date`
- `total_price`

### ServiceBookings Table
- `service_id` (Primary Key)
- `service_type`
- `room_no`
- `time_slot`
- `notes`
- `price`

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version)
- JDK 11 or higher
- Android SDK 23+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd HotelRoomBookingApp
   ```

2. **Open in Android Studio**
   - File → Open → Select project folder

3. **Sync Gradle**
   - Click "Sync Now" when prompted
   - Or File → Sync Project with Gradle Files

4. **Run the app**
   - Click Run button (▶️) or press Shift + F10
   - Select emulator or connected device

## 👤 Test Credentials

### Demo Account
- **Username**: `demo`
- **Password**: `demo123`

Or create your own account using the Register button!

## 📦 Pre-loaded Data

### Sample Rooms
- **Room 101** - Single (₹1000/night)
- **Room 102** - Double (₹1600/night)
- **Room 201** - Deluxe (₹2500/night)
- **Room 202** - Deluxe (₹2500/night)
- **Room 301** - Suite (₹4000/night)
- **Room 302** - Presidential Suite (₹4000/night)

### Services
- **Room Cleaning** - ₹200
- **Laundry Service** - ₹150
- **Food Delivery** - ₹300

## 🎯 Key Improvements

### From Previous Version
✅ Added user registration and authentication system  
✅ Implemented profile management  
✅ Added settings page  
✅ Enhanced database with user table  
✅ Added date picker for room bookings  
✅ Automatic price calculation  
✅ Session management with auto-login  
✅ Smooth screen transitions  
✅ Back navigation support  
✅ Material Design theme  
✅ Fixed all deprecated APIs  
✅ Added input validation  
✅ Improved error handling  
✅ Memory leak fixes  

## 🔧 Configuration

### Gradle Dependencies
- Material Components
- AppCompat
- ConstraintLayout
- RecyclerView
- CardView

### Theme
- Base: `Theme.MaterialComponents.Light.DarkActionBar`
- Primary Color: Blue (#2196F3)
- Accent Color: Orange (#FF9800)

## 📸 Screenshots

*(Add screenshots of your app here)*

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available under the MIT License.

## 👨‍💻 Developer

Developed as a comprehensive hotel booking solution with modern Android development practices.

## 📞 Support

For issues or questions, please open an issue in the repository.

---

**Version**: 1.0  
**Last Updated**: 2024  
**Status**: ✅ Production Ready
=======
# Hotel-Room-Booking-App
>>>>>>> 063e885487cd450ca2a6c24660db1efc92677913
