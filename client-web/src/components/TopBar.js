import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';


const useStyles = makeStyles((theme) => ({
	root: {
   		flexGrow: 1,
  	},
  	logo:{
  		height:'24px'
 	},
 	space: {
   		flexGrow: 1,
  	},
}));

export default function TopBar() {
	const classes = useStyles();

	return(
			<div className={classes.root}>
		      <AppBar position="static">
		        <Toolbar boxShadow={3}>
		          <img src="genericLogo.png" alt="logo" className={classes.logo} />
		          <div className={classes.space}/>
		          <Button variant="outlined" >Logout</Button>
		        </Toolbar>
		      </AppBar>
		    </div>

			 );

}

