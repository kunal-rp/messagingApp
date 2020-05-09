import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import TopBar from './TopBar'

class GenPage extends React.Component{

	render() {
		return(
			<div>
				<TopBar/>
				<Box m={3}>
					{this.props.children}
				</Box>
			</div>);
		}
}

export default GenPage;
