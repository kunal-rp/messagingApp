import React from 'react';
import logo from './logo.svg';
import { ThemeProvider as MuiThemeProvider } from '@material-ui/core/styles'
import './App.css';
import LoginPage from './components/login/LoginPage'
import HomeComp from './components/home/HomeComp'
import DashComp from './components/dash/DashComp'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import useMediaQuery from '@material-ui/core/useMediaQuery';
import {theme} from './utils/Theme'
import ProtectedRoute from './utils/ProtectedRoute'
import CssBaseline from '@material-ui/core/CssBaseline';

function App() {

   const prefersDarkMode = useMediaQuery('(prefers-color-scheme: dark)');
  return (
  	<Router>
    <div className="App">
    <MuiThemeProvider theme={theme(prefersDarkMode ? 'dark' : 'light')}>
    <CssBaseline />
        <div>
        <Switch>
          <Route path="/login">
            <LoginPage/>
          </Route>
           <Route path="/dash">
             <ProtectedRoute>
                <DashComp />
              </ProtectedRoute>
          </Route>
          <Route path="/">
            <HomeComp />
          </Route>
        </Switch>
        </div>
        </MuiThemeProvider>
      </div>
    </Router>

  );
}

export default App;
