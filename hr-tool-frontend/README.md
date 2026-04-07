# HR Tool Frontend

React TypeScript UI for the HR Tool job candidate management platform.

## Features

- View all job candidates
- Add new candidates with their skills
- Search candidates by name
- Search candidates by skills (multiple)
- Delete candidates
- Responsive design

## Prerequisites

- Node.js (v16 or higher)
- npm or yarn

## Installation

```bash
npm install
```

## Running the Application

### Development Mode

```bash
npm start
```

The application will start at `http://localhost:3000`

**Note:** Make sure the backend API is running at `http://localhost:8080`

### Build for Production

```bash
npm run build
```

## Project Structure

```
src/
├── components/
│   ├── CandidateForm.tsx      # Form for adding new candidates
│   └── CandidateList.tsx      # List view of all candidates
├── api/
│   └── axios.ts               # API client configuration
├── types/
│   └── index.ts               # TypeScript types
├── App.tsx                    # Main application component
├── App.css                    # Application styles
├── index.tsx                  # Entry point
└── index.css                  # Global styles
```

## Available Scripts

- `npm start` - Runs the app in development mode
- `npm run build` - Builds the app for production
- `npm test` - Runs the test suite
- `npm run eject` - Ejects from create-react-app

## API Integration

The frontend connects to the backend API at `http://localhost:8080/api`:

- `GET /candidates` - Get all candidates
- `POST /candidates` - Create new candidate
- `GET /candidates/{id}` - Get candidate by ID
- `PUT /candidates/{id}` - Update candidate
- `DELETE /candidates/{id}` - Delete candidate
- `GET /candidates/search/by-name?name=` - Search by name
- `GET /candidates/search/by-skills?skills=` - Search by skills
- `POST /candidates/{id}/skills` - Add skills to candidate
- `DELETE /candidates/{id}/skills/{skillName}` - Remove skill

## Styling

The application uses:
- CSS Grid for responsive layouts
- CSS Flexbox for component layouts
- CSS Media Queries for mobile responsiveness
- CSS Variables for theming (optional)

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## License

This project is part of the HR Tool platform.

