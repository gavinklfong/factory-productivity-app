import React from 'react';
import { Box, Text, Layer, Button, Heading } from 'grommet';
import { useDispatch } from 'react-redux';
import { closeMessageDialog } from './factorySlice';

export function MessageDialog(props) {

    const dispatch = useDispatch();
    
    const onClose = () => {
        dispatch(closeMessageDialog());
    }

    return (
        <Layer
          position="center"
          onClickOutside={onClose}
          onEsc={onClose}
        >
          <Box pad="medium" gap="small" width="medium">
            <Text>{props.message}</Text>
            <Box
              as="footer"
              gap="small"
              direction="row"
              align="center"
              justify="end"
              pad={{ top: 'medium', bottom: 'small' }}
            >
              <Button label="Close" primary onClick={onClose} />
            </Box>
          </Box>
        </Layer>
    );
}