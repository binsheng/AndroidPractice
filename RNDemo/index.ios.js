/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

export default class RNDemo extends Component {
  render() {
    return (
      <View style={{flex: 1,flexDirection: 'column',justifyContent: 'center',alignItems: 'stretch'}}>
        <View style={{height:50,backgroundColor: 'powderblue'}}></View>
        <View style={{height:50,backgroundColor: 'skyblue'}}></View>
        <View style={{height:50,backgroundColor: 'steelblue'}}></View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('RNDemo', () => RNDemo);
