import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import { RoutePath } from '../interface/RoutePath';
import HomePage from './home/HomePage';

const AppRoutes = () => {
    return (
      <Router>
          <Routes>
              <Route path={RoutePath.HOMEPAGE} element={<HomePage />} />
          </Routes>
      </Router>
    )
  }
  
  export default AppRoutes