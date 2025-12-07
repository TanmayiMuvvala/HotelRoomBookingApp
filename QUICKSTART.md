# ⚡ Quick Start Guide

## 🚀 Run the App in 3 Steps

### 1️⃣ Build
```bash
./gradlew assembleDebug
```

### 2️⃣ Run
- Click the green **Run** button ▶️ in Android Studio
- Or press **Shift + F10**

### 3️⃣ Login
Use the demo account:
- **Username**: `demo`
- **Password**: `demo123`

## 🎯 First Actions

### Try These Features:

1. **Browse Rooms**
   - Dashboard → View Rooms
   - Tap any room to see details

2. **Book a Room**
   - Room Details → Book Now
   - Select dates → Confirm

3. **Request Service**
   - Dashboard → Hotel Services
   - Choose service → Fill details → Confirm

4. **View History**
   - Dashboard → Booking History
   - See all your bookings

5. **Update Profile**
   - Settings (⚙️) → My Profile
   - Edit info → Update

## 📱 App Flow

```
Splash Screen (2s)
    ↓
Login Screen
    ↓
Dashboard (Main Hub)
    ├── View Rooms → Room Details → Book Room
    ├── Hotel Services → Book Service
    ├── Booking History
    └── Settings → Profile / About / Logout
```

## 🎨 What You'll See

### Dashboard
- Welcome message with your name
- 3 main cards: Rooms, Services, History
- Settings icon (top right)
- Logout button (bottom)

### Rooms
- 6 pre-loaded rooms
- Prices: ₹1000 - ₹4000/night
- Types: Single, Double, Deluxe, Suite

### Services
- Room Cleaning (₹200)
- Laundry (₹150)
- Food Delivery (₹300)

## 🔑 Test Accounts

### Demo User (Pre-loaded)
```
Username: demo
Password: demo123
Name: Demo User
Email: demo@hotel.com
Phone: 1234567890
```

### Create Your Own
- Login Screen → Register
- Fill the form → Register
- Login with new credentials

## 💡 Pro Tips

1. **Date Picker**: Tap the date fields to open calendar
2. **Auto-Calculate**: Price updates automatically when you change dates
3. **Back Button**: Works everywhere for easy navigation
4. **Stay Logged In**: App remembers you after closing
5. **Quick Logout**: Settings → Logout

## 🐛 Troubleshooting

### App Won't Build?
```bash
./gradlew clean
./gradlew assembleDebug
```

### Can't Login?
- Use demo account: `demo` / `demo123`
- Or create new account via Register

### Dates Not Working?
- Tap the date field (don't type)
- Calendar will appear
- Select date from picker

## 📊 What's Included

✅ 6 Sample Rooms  
✅ 1 Demo User  
✅ 3 Service Types  
✅ Full Authentication  
✅ Profile Management  
✅ Booking System  
✅ History Tracking  

## 🎉 You're Ready!

The app is fully functional and ready to use. Explore all features and enjoy the seamless hotel booking experience!

---

**Need Help?** Check `USER_GUIDE.md` for detailed instructions.  
**Want Details?** See `FEATURES.md` for complete feature list.  
**Technical Info?** Read `README.md` for architecture details.
