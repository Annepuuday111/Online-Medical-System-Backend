<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:FF6B6B,35:FFA94D,70:4DABF7,100:845EF7&height=200&section=header&text=Online%20Medical%20System%20API&fontSize=44&fontColor=ffffff&fontAlignY=36&animation=fadeIn&desc=Spring%20Boot%20%2B%20MySQL%20REST%20backend&descSize=16&descAlignY=58" width="100%"/>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=22&pause=1000&color=845EF7&center=true&vCenter=true&width=700&lines=Spring+Boot+3+REST+API;Doctors%2C+patients+and+appointments;Prescriptions%2C+orders+and+feedback;Spring+Data+JPA+%2B+MySQL" alt="Typing SVG"/>

<br/>

<img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>

<br/>

<img src="https://img.shields.io/badge/Status-Completed-20C997?style=for-the-badge"/>
<img src="https://img.shields.io/github/stars/Annepuuday111/Online-Medical-System-Backend?style=for-the-badge&color=845EF7"/>
<img src="https://img.shields.io/github/last-commit/Annepuuday111/Online-Medical-System-Backend?style=for-the-badge&color=4DABF7"/>
<img src="https://img.shields.io/github/repo-size/Annepuuday111/Online-Medical-System-Backend?style=for-the-badge&color=FF6B6B"/>

<br/><br/>

[About](#-about) • [Features](#-features) • [Tech Stack](#️-tech-stack) • [API Endpoints](#-api-endpoints) • [Project Structure](#-project-structure) • [Getting Started](#-getting-started) • [Related Repos](#-related-repositories)

</div>

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## 📖 About

This repository is the **REST API** behind the Online Medical System. It is a Spring Boot 3 application that stores data in MySQL through Spring Data JPA and exposes JSON endpoints consumed by the React frontend.

- ⚛️ **Frontend:** [Online-Medical-System-Frontend](https://github.com/Annepuuday111/Online-Medical-System-Frontend) — live at [online-medical-system.vercel.app](https://online-medical-system.vercel.app)
- 🏛️ **Original version:** [Online-Medical-Management-System](https://github.com/Annepuuday111/Online-Medical-Management-System) — the first build of this project using Java Servlets & JSP

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## ✨ Features

- 👤 **Users (patients)** — register, log in, change password, list / delete users, look up a user's name by email
- 👨‍⚕️ **Doctors** — CRUD, doctor login, lookup by id, by `doctorId` or by email
- 🩺 **Specialists** — CRUD for medical specialities
- 📅 **Appointments** — create, update (e.g. status), delete, and list appointments per doctor
- 💊 **Medicines** — doctors attach medicines (name, dosage, frequency) to an appointment
- 🛒 **Orders** — save and list medicine orders
- ⭐ **Feedback** — CRUD for patient feedback with rating and recommendation
- 📊 **Count endpoints** for dashboard statistics (users, doctors, specialists, appointments, orders)
- 🌐 CORS configured for `http://localhost:3000` and the Vercel deployment

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## 🛠️ Tech Stack

<div align="center">

<img src="https://skillicons.dev/icons?i=java,spring,hibernate,mysql,maven&theme=light"/>

</div>

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.3.5 (Spring Web, DevTools) |
| Persistence | Spring Data JPA / Hibernate, `ddl-auto=update` |
| Database | MySQL (`mysql-connector-j`) |
| Validation | Jakarta Validation API + Hibernate Validator |
| Build | Maven (wrapper included: `mvnw`, `mvnw.cmd`) |
| Frontend (separate repo) | React — [Online-Medical-System-Frontend](https://github.com/Annepuuday111/Online-Medical-System-Frontend) |

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## 🔌 API Endpoints

<details open>
<summary><b>👤 Users</b> — <code>/api/users</code></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/users/register` | Register a new user |
| `POST` | `/api/users/login` | User login |
| `POST` | `/api/users/{userId}/change-password` | Change a user's password |
| `GET` | `/api/users` | List all users |
| `GET` | `/api/users/name?email=` | Get a user's name by email |
| `DELETE` | `/api/users/{id}` | Delete a user |
| `GET` | `/api/users/counts` | Total number of users |

</details>

<details open>
<summary><b>👨‍⚕️ Doctors</b> — <code>/api/doctors</code></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/doctors` | List all doctors |
| `POST` | `/api/doctors` | Add a doctor |
| `GET` | `/api/doctors/{id}` | Get a doctor by database id |
| `GET` | `/api/doctors/doctorId/{doctorId}` | Get a doctor by doctor ID |
| `GET` | `/api/doctors/details?email=` | Get a doctor by email |
| `PUT` | `/api/doctors/{id}` | Update a doctor |
| `DELETE` | `/api/doctors/{id}` | Delete a doctor |
| `POST` | `/api/doctors/login` | Doctor login |
| `GET` | `/api/doctors/counts` | Total number of doctors |

</details>

<details open>
<summary><b>🩺 Specialists</b> — <code>/api/specialists</code></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/specialists` | List specialists |
| `POST` | `/api/specialists` | Add a specialist |
| `GET` | `/api/specialists/{id}` | Get a specialist |
| `PUT` | `/api/specialists/{id}` | Update a specialist |
| `DELETE` | `/api/specialists/{id}` | Delete a specialist |
| `GET` | `/api/specialists/count` | Total number of specialists |

</details>

<details open>
<summary><b>📅 Appointments</b> — <code>/api/appointments</code></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/appointments` | List all appointments |
| `POST` | `/api/appointments` | Book an appointment |
| `GET` | `/api/appointments/{id}` | Get an appointment |
| `PUT` | `/api/appointments/{id}` | Update an appointment |
| `DELETE` | `/api/appointments/{id}` | Delete an appointment |
| `GET` | `/api/appointments/doctors` | List doctors (for the booking form) |
| `GET` | `/api/appointments/doctor/{doctorId}` | Appointments for a doctor |
| `GET` | `/api/appointments/counts` | Total number of appointments |

</details>

<details open>
<summary><b>💊 Medicines, 🛒 Orders & ⭐ Feedback</b></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/medicine/{appointmentId}` | Suggest a medicine for an appointment |
| `GET` | `/api/medicine/{appointmentId}` | Medicines for an appointment |
| `POST` | `/api/orders/save` | Save a medicine order |
| `GET` | `/api/orders` | List orders |
| `GET` | `/api/orders/counts` | Total number of orders |
| `GET` | `/api/feedback/feedbacks` | List feedback |
| `POST` | `/api/feedback/feedbacks` | Add feedback |
| `GET` | `/api/feedback/feedbacks/{id}` | Get feedback |
| `PUT` | `/api/feedback/feedbacks/{id}` | Update feedback |
| `DELETE` | `/api/feedback/feedbacks/{id}` | Delete feedback |

</details>

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## 📂 Project Structure

```
Online-Medical-System-Backend/
├── src/main/java/com/klef/medical_system/
│   ├── MedicalSystemApplication.java
│   ├── config/          # CorsConfig, SecurityConfig
│   ├── controller/      # Appointment, Doctor, Feedback, Medicine, Order, Specialist, User
│   ├── exception/       # ResourceNotFoundException
│   ├── model/           # JPA entities: Appointment, Doctor, Feedback, Medicine, Order, Specialist, User
│   ├── repository/      # Spring Data JPA repositories
│   └── service/         # Service layer
├── src/main/resources/
│   └── application.properties
├── src/test/java/...    # Spring Boot context test
├── mvnw, mvnw.cmd
└── pom.xml
```

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## 🚀 Getting Started

### Prerequisites

- JDK 17
- MySQL server
- Maven (or use the included `./mvnw` wrapper)

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Annepuuday111/Online-Medical-System-Backend.git
   cd Online-Medical-System-Backend
   ```
2. **Create a database**
   ```sql
   CREATE DATABASE medical_system;
   ```
3. **Configure** `src/main/resources/application.properties` with your own connection details:
   ```properties
   server.port=8080
   spring.datasource.url=jdbc:mysql://localhost:3306/medical_system
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   spring.jpa.hibernate.ddl-auto=update
   ```
   Tables are created automatically by Hibernate on first run.
4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

> ℹ️ The committed `application.properties` sets `server.port=8082`, while the [frontend](https://github.com/Annepuuday111/Online-Medical-System-Frontend) calls `http://localhost:8080/api/...`. Set the port to `8080` (as above) when running both locally.

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## 🔗 Related Repositories

| Repository | Description |
|------------|-------------|
| [Online-Medical-System-Frontend](https://github.com/Annepuuday111/Online-Medical-System-Frontend) | React frontend that consumes this API |
| [Online-Medical-Management-System](https://github.com/Annepuuday111/Online-Medical-Management-System) | Original version built with Java Servlets, JSP and MySQL |

## 🗺️ Roadmap

- [ ] Move database credentials to environment variables
- [ ] Hash passwords with the configured `BCryptPasswordEncoder`
- [ ] Add update / delete endpoints for orders

## 🤝 Contributing

Contributions are welcome! Fork the repo, create a feature branch, commit your changes and open a pull request.

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

## 👨‍💻 Author

<div align="center">

### Annepu Uday Kumar

<a href="https://github.com/Annepuuday111"><img src="https://img.shields.io/badge/GitHub-Annepuuday111-845EF7?style=for-the-badge&logo=github&logoColor=white"/></a>
<a href="https://annepuudaykumar.netlify.app"><img src="https://img.shields.io/badge/Portfolio-annepuudaykumar.netlify.app-FF6B6B?style=for-the-badge&logo=googlechrome&logoColor=white"/></a>

</div>

<br/>

<div align="center">⭐ If you like this project, give it a star!</div>

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:845EF7,35:4DABF7,70:FFA94D,100:FF6B6B&height=120&section=footer" width="100%"/>
