import { configureStore } from '@reduxjs/toolkit';
import factoryReducer from '../features/factory/factorySlice'

export const store = configureStore({
  reducer: {
    factory: factoryReducer,
  },
});
