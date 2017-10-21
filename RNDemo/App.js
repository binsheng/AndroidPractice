import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

import LifecycleComponent from './LifecycleComponent'


export default class App extends React.Component {




  render() {
    return (
      <View style={styles.container}>
          <LifecycleComponent/>
      </View>
    );
  }


}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'stretch',
    justifyContent: 'center',

  },
});
