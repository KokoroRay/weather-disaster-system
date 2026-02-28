import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Navigation } from './components/Navigation';
import { CloudBackground } from './components/CloudBackground';

import { ForecastPage } from './pages/ForecastPage';
import { DisastersPage } from './pages/DisastersPage';
import { AuthPage } from './pages/AuthPage';

function App() {
  return (
    <Router>
      <CloudBackground />
      <Navigation />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/forecast" element={<ForecastPage />} />
        <Route path="/disasters" element={<DisastersPage />} />
        <Route path="/auth" element={<AuthPage />} />
      </Routes>
    </Router>
  );
}

export default App;
