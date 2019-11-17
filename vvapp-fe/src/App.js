import React from 'react';
import Seniors from './components/seniors/seniors'
import SeniorService from './services/senior.service'
import styles from './App.module.css'

class App extends React.Component{

  constructor(props){
    super(props);
    this.seniorService = new SeniorService();

    this.state = {
        events: [],
        seniors: [],
        eventRoles: [],
        eventTypes: []
    }
  }

  componentDidMount(){
    this.getSeniors();
  }

  render() {
    if(!this.state.seniors) return null;

    return (
      <div>
        <Seniors className={styles.seniors} seniors={this.state.seniors} />
      </div>
    );
  }

  getSeniors(){
    this.seniorService.getAllSeniors().then(seniors => {
      this.setState({seniors: seniors});
    })
  }
}

export default App;
